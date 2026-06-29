package es.daw.extra_api_peliculas.dto.response;

public record ActorResponseDto(
        Long id,
        String stageName,
        String fullName,
        String nationality,
        boolean active
) {
}
