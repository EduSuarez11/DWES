package es.daw.hotel_api.service;

import es.daw.hotel_api.dto.hotel.HotelRequestDto;
import es.daw.hotel_api.dto.hotel.HotelResponseDto;
import es.daw.hotel_api.entity.Categoria;
import es.daw.hotel_api.entity.Hotel;
import es.daw.hotel_api.exception.CategoriaNotFoundException;
import es.daw.hotel_api.mapper.HotelMapper;
import es.daw.hotel_api.repository.CategoriaRepository;
import es.daw.hotel_api.repository.HotelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HotelService {

    private final HotelRepository hotelRepository;
    private final CategoriaRepository categoriaRepository;

    public List<HotelResponseDto> getHotels(String codigoCategoria, String localidad) {
        List<Hotel> hotels;

        if (codigoCategoria != null && localidad != null) {
            log.info("Buscando hoteles por codigo: {} y localidad: {}", codigoCategoria, localidad);
            hotels = hotelRepository.findByLocalidadAndCategoria_Codigo(localidad, codigoCategoria);
        } else if (codigoCategoria != null) {
            log.info("Buscando hoteles por codigo: {}", codigoCategoria);
            hotels = hotelRepository.findByCategoria_Codigo(codigoCategoria.toUpperCase());
        } else if (localidad != null) {
            log.info("Buscando hoteles por localidad: {}", localidad);
            hotels = hotelRepository.findByLocalidad(localidad);
        } else {
            log.info("Buscando todos los hoteles");
            hotels = hotelRepository.findAll();
        }

        log.info("Hoteles encontrados: {}", hotels);

        return hotels.stream()
                .map(HotelMapper::hotelToHotelResponseDto)
                .toList();
    }

    @Transactional
    public HotelResponseDto createHotel(HotelRequestDto dto) {
        Hotel hotel = new Hotel();
        Categoria categoria = categoriaRepository.findByCodigo(dto.codigoCategoria())
                .orElseThrow(() -> new CategoriaNotFoundException("Categoria no encontrada"));
        hotel.setCodigo(dto.codigo());
        hotel.setNombre(dto.nombre());
        hotel.setDescripcion(dto.descripcion());
        hotel.setPiscina(dto.piscina());
        hotel.setLocalidad(dto.localidad());
        categoria.addHotel(hotel);

        return HotelMapper.hotelToHotelResponseDto(hotel);
    }
}
