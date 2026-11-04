package es.daw.simulacro.mvc.mvc_hoteles.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {
    private String username;
    private String password;
    private String fullName;
    private String email;
}
