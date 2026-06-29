package es.daw.extra_api_peliculas.dto.response;

public record ActorCastDto(
        Long actorId,
        String stageName,
        String characterName, // Lo sacamos del MovieCast
        Integer screenMinutes // Lo sacamos del MovieCast
) {}
