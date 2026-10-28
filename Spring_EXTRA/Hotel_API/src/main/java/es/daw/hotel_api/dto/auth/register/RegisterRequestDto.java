package es.daw.hotel_api.dto.auth.register;

public record RegisterRequestDto(
        String username,
        String password,
        String fullName,
        String email
) {}
