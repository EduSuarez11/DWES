package es.daw.hotel_api.service;


import es.daw.hotel_api.dto.reserva.CreateReservaRequestDto;
import es.daw.hotel_api.dto.reserva.ReservasUsuarioRequestDto;
import es.daw.hotel_api.dto.reserva.ReservasUsuarioResponseDto;
import es.daw.hotel_api.entity.AppUser;
import es.daw.hotel_api.entity.Habitacion;
import es.daw.hotel_api.entity.Reserva;
import es.daw.hotel_api.repository.AppUserRepository;
import es.daw.hotel_api.repository.HabitacionRepository;
import es.daw.hotel_api.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final AppUserRepository appUserRepository;
    private final HabitacionRepository habitacionRepository;

    /// Ver las reservas que ha hecho un usuario
    public List<ReservasUsuarioResponseDto> getReservationsForUser(ReservasUsuarioRequestDto dto) {
        AppUser user = appUserRepository.findByUsername(dto.fullName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return reservaRepository.getReservationsUserRoom(user.getFullName());
    }


    /// Crear una reserva de una habitacion
    public ReservasUsuarioResponseDto createReservation(CreateReservaRequestDto dto) {
        AppUser user = appUserRepository.findByFullName(dto.fullName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Habitacion habitacion = habitacionRepository.findByCodigo(dto.codigoHabitacion())
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));

        Reserva reserva = new Reserva();
        reserva.setHabitacion(habitacion);
        reserva.setUser(user);

        return null;
    }
}
