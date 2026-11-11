package es.daw.hotel_api.dto.auth.register;

import es.daw.hotel_api.entity.Role;

public record RegisterResponseDto(
        String username,
        String fullName,
        String password,
        String email,
        String role
) {}
