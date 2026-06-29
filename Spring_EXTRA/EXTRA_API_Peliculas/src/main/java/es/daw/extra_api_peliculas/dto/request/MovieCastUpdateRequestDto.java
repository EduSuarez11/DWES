package es.daw.extra_api_peliculas.dto.request;

import java.math.BigDecimal;

public record MovieCastUpdateRequestDto(
        Integer screenMinutes,
        BigDecimal salaryOverride
) {
}
