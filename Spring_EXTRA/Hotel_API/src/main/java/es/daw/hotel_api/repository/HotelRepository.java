package es.daw.hotel_api.repository;

import es.daw.hotel_api.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByLocalidad(String localidad);
    Optional<Hotel> findByCodigo(String codigo);
    List<Hotel> findByCategoria_Codigo(String codigoCategoria);
    List<Hotel> findByLocalidadAndCategoria_Codigo(String localidad, String codigoCategoria);
}
