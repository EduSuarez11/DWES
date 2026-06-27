package es.daw.extra_estudiantes_mvc.dto.auth;

public record AuthRequest(
        String username,
        String password
) {}
