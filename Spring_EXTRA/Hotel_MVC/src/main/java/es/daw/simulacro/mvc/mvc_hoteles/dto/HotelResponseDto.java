package es.daw.simulacro.mvc.mvc_hoteles.dto;

public record HotelResponseDto(
        String codigo,
        String nombre,
        String descripcion,
        boolean piscina,
        String localidad,
        CategoriaResponseDto categoria
) {}
