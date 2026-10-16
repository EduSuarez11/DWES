package es.daw.hotel_api.repository;

import es.daw.hotel_api.dto.habitacion.HabitacionResponseDto;
import es.daw.hotel_api.entity.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
    Optional<Habitacion> findByCodigo(String codigo);

    @Query("""
    SELECT new es.daw.hotel_api.dto.habitacion.HabitacionResponseDto(
        h.codigo,
        h.tamano,
        h.doble,
        h.precioNoche,
        h.incluyeDesayuno,
        h.ocupada,
        htl.codigo
    ) FROM Habitacion h JOIN h.hotel htl
        WHERE h.precioNoche >= :precioMinimo
            AND h.precioNoche <= :precioMaximo
            AND h.tamano >= :tamanoMinimo
            AND htl.codigo = :codigo
    """)
    List<HabitacionResponseDto> searchRoomsByFilter(
            @Param("codigo") String codigo,
            @Param("tamanoMinimo") int tamanoMinimo,
            @Param("precioMaximo") int precioMaximo,
            @Param("precioMinimo") int precioMinimo
    );

    @Query("""
        SELECT new es.daw.hotel_api.dto.habitacion.HabitacionResponseDto(
            hab.codigo,
            hab.tamano,
            hab.doble,
            hab.precioNoche,
            hab.incluyeDesayuno,
            hab.ocupada,
            hot.codigo
        )
        FROM Habitacion hab
            JOIN hab.hotel hot
            WHERE hab.ocupada = true
    """)
    List<HabitacionResponseDto> findByOccupiedRoom();
}
