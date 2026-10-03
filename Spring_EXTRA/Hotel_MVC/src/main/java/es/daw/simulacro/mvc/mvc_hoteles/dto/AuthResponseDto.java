package es.daw.simulacro.mvc.mvc_hoteles.dto;

public record AuthResponseDto(
        String token
) {
    public String getToken() {
        return token;
    }
}
