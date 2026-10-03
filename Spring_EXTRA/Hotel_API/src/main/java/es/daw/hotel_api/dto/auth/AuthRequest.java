package es.daw.hotel_api.dto.auth;

public record AuthRequest(
        String username,
        String password
) {}
