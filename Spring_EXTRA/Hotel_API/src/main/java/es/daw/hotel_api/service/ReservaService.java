package es.daw.hotel_api.service;


import es.daw.hotel_api.dto.reserva.CreateReservaRequestDto;
import es.daw.hotel_api.dto.reserva.CreateReservaResponseDto;
import es.daw.hotel_api.dto.reserva.ReservasUsuarioResponseDto;
import es.daw.hotel_api.entity.AppUser;
import es.daw.hotel_api.entity.Habitacion;
import es.daw.hotel_api.entity.Reserva;
import es.daw.hotel_api.mapper.ReservaMapper;
import es.daw.hotel_api.repository.AppUserRepository;
import es.daw.hotel_api.repository.HabitacionRepository;
import es.daw.hotel_api.repository.ReservaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final AppUserRepository appUserRepository;
    private final HabitacionRepository habitacionRepository;

    /// Ver las reservas que ha hecho un usuario
    public List<ReservasUsuarioResponseDto> getReservationsForUser(Long userId) {
//        AppUser user = appUserRepository.findByEmail(dto.email())
//                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return reservaRepository.getReservationsUserRoom(userId);
    }


    /// Crear una reserva de una habitacion
    @Transactional
    public CreateReservaResponseDto createReservation(CreateReservaRequestDto dto, String codigoHabitacion) {
        AppUser user = appUserRepository.findByEmail(dto.email())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Habitacion habitacion = habitacionRepository.findByCodigo(codigoHabitacion)
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));

        // Comprobar que la fecha es posterior a la actual y que la fecha de salida es posterior a la fecha de entrada
        if (dto.fechaEntrada().isBefore(LocalDate.now())) throw new RuntimeException("La fecha de entrada debe ser posterior a la fecha actual");
        else if (!dto.fechaSalida().isAfter(dto.fechaEntrada())) throw new RuntimeException("La fecha de salida debe ser posterior a la fecha de entrada");


        // Validacion para que no se puedan reservar dos habitaciones iguales en la misma fecha
        List<Reserva> reservasDeHabitacion = reservaRepository.findReservaByCodigoHabitacion(codigoHabitacion);

        boolean habitacionOcupada = reservasDeHabitacion.stream()
                .anyMatch(r -> dto.fechaEntrada().isBefore(r.getFechaSalida()) && dto.fechaSalida().isAfter(r.getFechaEntrada()));

        if (habitacionOcupada) {
            throw new RuntimeException("Las fechas seleccionadas se encuentran en otra reserva existente para esta habitación");
        }

        // Calcular el precio total / noche
        long dayDifference = ChronoUnit.DAYS.between(dto.fechaEntrada(), dto.fechaSalida());
        BigDecimal precioTotal = habitacion.getPrecioNoche().multiply(BigDecimal.valueOf(dayDifference));

        Reserva reserva = new Reserva();
        reserva.setHabitacion(habitacion);
        reserva.setUser(user);
        reserva.setFechaEntrada(dto.fechaEntrada());
        reserva.setFechaSalida(dto.fechaSalida());
        reserva.setPrecioTotal(precioTotal);
        reserva.setFechaCreacion(LocalDateTime.now());

        habitacion.addReservas(reserva);
        user.addReservas(reserva);

        return ReservaMapper.toCreateReservaResponseDto(reserva);
    }
}
