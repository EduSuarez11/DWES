package es.daw.hotel_api.repository;

import es.daw.hotel_api.dto.habitacion.HabitacionResponseDto;
import es.daw.hotel_api.dto.reserva.ReservasUsuarioResponseDto;
import es.daw.hotel_api.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("""
        SELECT new es.daw.hotel_api.dto.reserva.ReservasUsuarioResponseDto(
            r.fechaEntrada,
            r.fechaSalida,
            r.precioTotal,
            r.fechaCreacion,
            u.fullName,
            h.codigo
        ) FROM Reserva r
            JOIN r.user u
            JOIN r.habitacion h
                WHERE u.fullName =: fullNameUser
    """)
    List<ReservasUsuarioResponseDto> getReservationsUserRoom(String fullNameUser);
}
