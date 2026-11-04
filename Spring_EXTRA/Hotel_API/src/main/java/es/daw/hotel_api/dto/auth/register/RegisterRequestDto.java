package es.daw.hotel_api.dto.auth.register;

import jakarta.validation.constraints.*;

public record RegisterRequestDto(
        @NotNull(message = "* El nombre de usuario no puede ser nulo.")
        @NotBlank(message = "* El nombre de usuario no puede estar vacío.")
        @Max(value = 80, message = "* El nombre de usuario supera los 80 carácteres.")
        @Min(value = 3, message = "* El nombre de usuario no supera los 3 carácteres.")
        String username,

        @NotNull(message = "* La contraseña no puede ser nula.")
        @NotBlank(message = "* La contraseña no puede estar vacía")
        @Max(value = 25, message = "* La contraseña supera los 80 carácteres.")
        @Min(value = 3, message = "* La contraseña no supera los 3 carácteres.")
        String password,

        @NotNull(message = "* El nombre completo no puede ser nulo.")
        @NotBlank(message = "* El nombre completo no puede estar vacío.")
        @Max(value = 120, message = "* El nombre completo supera los 120 carácteres.")
        @Min(value = 3, message = "* El nombre completo no supera los 3 carácteres.")
        String fullName,

        @Email(regexp = "/[^@]+@[^@]+\\.[^@]+/", message = "Formato de email incorrecto.")
        @NotNull(message = "* El email no puede ser nulo.")
        @NotBlank(message = "* El email no puede quedar vacío.")
        String email
) {}
