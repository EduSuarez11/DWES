package es.daw.extra_api_peliculas.dto.response;

import java.math.BigDecimal;

public record MovieCastUpdateResponseDto(
        Long movieId,
        Long actorId,
        String characterName,
        Integer screenMinutes,
        BigDecimal salaryOverride
) {
}
