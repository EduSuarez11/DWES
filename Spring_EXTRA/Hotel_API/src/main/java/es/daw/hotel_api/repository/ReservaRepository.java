package es.daw.hotel_api.repository;


import es.daw.hotel_api.dto.reserva.ReservasUsuarioResponseDto;
import es.daw.hotel_api.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("""
        SELECT r
            FROM Reserva r
            JOIN r.habitacion h
                WHERE h.codigo = :codigoHabitacion
    """)
    List<Reserva> findReservaByCodigoHabitacion(@Param("codigoHabitacion") String codigoHabitacion);

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
                WHERE u.id = :userId
    """)
    List<ReservasUsuarioResponseDto> getReservationsUserRoom(@Param("userId") Long userId);

}
