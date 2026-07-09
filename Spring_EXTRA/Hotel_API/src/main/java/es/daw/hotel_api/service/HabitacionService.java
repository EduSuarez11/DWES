package es.daw.hotel_api.service;

import es.daw.hotel_api.dto.habitacion.HabitacionRequestDto;
import es.daw.hotel_api.dto.habitacion.HabitacionResponseDto;
import es.daw.hotel_api.entity.Habitacion;
import es.daw.hotel_api.entity.Hotel;
import es.daw.hotel_api.exception.HabitacionNotFoundException;
import es.daw.hotel_api.exception.HotelNotFoundException;
import es.daw.hotel_api.mapper.HabitacionMapper;
import es.daw.hotel_api.repository.HabitacionRepository;
import es.daw.hotel_api.repository.HotelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HabitacionService {

    private final HabitacionRepository habitacionRepository;
    private final HotelRepository hotelRepository;

    public List<HabitacionResponseDto> searchRooms(String codHotel, Integer tamanoMinimo, Integer precioMinimo, Integer precioMaximo) {
        List<Habitacion> habitaciones;

        if (codHotel != null && tamanoMinimo != null && precioMaximo != null && precioMinimo != null) {
            log.info("Busca por todos los filtros");
            return habitacionRepository.searchRoomsByFilter(codHotel, tamanoMinimo, precioMaximo, precioMinimo);
        } else if (codHotel != null) {
            log.info("Busca por codigo hotel");
            Hotel hotel = hotelRepository.findByCodigo(codHotel)
                    .orElseThrow(() -> new HotelNotFoundException("Hotel no encontrado"));
            habitaciones = new ArrayList<>();
            hotel.getHabitaciones().forEach(habitacion -> {
                if (!habitacion.isOcupada()) {
                    habitaciones.add(habitacion);
                }
            });

            log.info("Habitaciones encontradas: {}", habitaciones);
        } else {
            log.info("Busca todas las habitaciones");
            habitaciones = habitacionRepository.findAll();
        }

        return habitaciones.stream()
                .map(HabitacionMapper::habitacionToHabitacionResponseDto)
                .toList();
    }


    @Transactional
    public HabitacionResponseDto registerRoom(HabitacionRequestDto dto, String codHotel) {
        Hotel hotel = hotelRepository.findByCodigo(codHotel)
                .orElseThrow(() -> new HotelNotFoundException("Hotel no encontrado"));

        Habitacion habitacion = new Habitacion();
        habitacion.setCodigo(dto.codigo());
        habitacion.setTamano(dto.tamano());
        habitacion.setDoble(dto.doble());
        habitacion.setPrecioNoche(dto.precioNoche());
        habitacion.setIncluyeDesayuno(dto.incluyeDesayuno());

        hotel.addHabitacion(habitacion);

        return HabitacionMapper.habitacionToHabitacionResponseDto(habitacion);
    }


    @Transactional
    public void deleteRoom(String codigoHabitacion) {
        Habitacion habitacion = habitacionRepository.findByCodigo(codigoHabitacion)
                .orElseThrow(() -> new HabitacionNotFoundException("Habitación no encontrada"));

        log.info("Habitacion con Hotel: {} eliminada", habitacion.getHotel());
        habitacion.getHotel().removeHabitacion(habitacion);
    }
}
