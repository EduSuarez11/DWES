package es.daw.extra_api_peliculas.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

// Body (payload) para POST /movies/{id}/cast
// Añadir actor a película. Implica añadir un personaje a la película en base a un actor que ya existe
// por defecto se crea active = true (activo)
public record MovieCastRequestDto (
        @NotNull(message = "El id del actor es obligatorio")
        Long actorId,

        @NotBlank(message = "El nombre del personaje no puede estar vacío")
        @Size(max = 80, message = "El nombre del personaje no puede superar 80 caracteres")
        String characterName,

        @NotNull(message = "Los minutos en pantalla son obligatorios")
        @Min(value = 1, message = "Los minutos deben ser al menos de 1")
        Short screenMinutes,

        @DecimalMin(value="0.0", message = "El salario no puede ser negativo")
        @Digits(integer = 10, fraction = 2, message = "El salario debe tener como máximo 12 dígitos y 2 decimales")
        BigDecimal salaryOverride
)
{}
