package es.daw.hotel_api.dto.hotel;

import es.daw.hotel_api.dto.CategoriaResponseDto;

public record HotelResponseDto(
        String codigo,
        String nombre,
        String descripcion,
        boolean piscina,
        String localidad,
        CategoriaResponseDto categoria
) {}
