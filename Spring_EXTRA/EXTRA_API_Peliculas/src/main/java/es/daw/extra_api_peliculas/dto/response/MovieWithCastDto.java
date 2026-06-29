package es.daw.extra_api_peliculas.dto.response;

import es.daw.extra_api_peliculas.enums.GenreType;

import java.util.List;

public record MovieWithCastDto(
        Long id,
        String title,
        int releaseYear,
        GenreType genre,
        boolean active,
        List<ActorCastDto> cast
) {}
