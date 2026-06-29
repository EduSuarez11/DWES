package es.daw.extra_api_peliculas.dto.response;

import es.daw.extra_api_peliculas.enums.GenreType;

import java.math.BigDecimal;

public record TopGrossingMovieReport(
        Long   movieId,
        String title,
        GenreType genre,
        Long   totalEntries,      // número de registros de taquilla
        Long   totalScreens,      // suma de pantallas acumuladas
        BigDecimal totalGross     // recaudación total
) {}