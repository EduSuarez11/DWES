package es.daw.extra_api_peliculas.dto;

import java.time.LocalDateTime;

public record ErrorResponseDto(
        LocalDateTime timestamp,
        int status,
        String error,      // Texto HTTP estándar: "Not Found", "Conflict", etc.
        String message,    // Mensaje de negocio legible
        String path        // URI que originó el error
) {}