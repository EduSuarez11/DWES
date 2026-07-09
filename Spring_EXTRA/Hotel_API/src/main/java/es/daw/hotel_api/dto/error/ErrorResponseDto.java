package es.daw.hotel_api.dto.error;

import java.time.LocalDateTime;

public record ErrorResponseDto(
        LocalDateTime timestamp,
        String error
) {}
