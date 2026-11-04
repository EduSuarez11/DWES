package es.daw.simulacro.mvc.mvc_hoteles.dto.hotel;

import es.daw.simulacro.mvc.mvc_hoteles.dto.categoria.CategoriaResponseDto;

public record HotelResponseDto(
        String codigo,
        String nombre,
        String descripcion,
        boolean piscina,
        String localidad,
        CategoriaResponseDto categoria
) {}
