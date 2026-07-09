package es.daw.hotel_api.dto.hotel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HotelRequestDto(
        @NotNull(message = "El código de hotel no debe ser nulo")
        String codigo,
        @NotNull(message = "El nombre del hotel no puede ser nulo")
        @NotBlank(message = "El nombre del hotel no puede estar vacío")
        String nombre,
        String descripcion,
        boolean piscina,
        String localidad,
        @NotNull(message = "El código de categoría no debe ser nulo")
        String codigoCategoria
) {}
