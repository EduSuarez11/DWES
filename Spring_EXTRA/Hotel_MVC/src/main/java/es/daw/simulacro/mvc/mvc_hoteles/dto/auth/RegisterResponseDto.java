package es.daw.simulacro.mvc.mvc_hoteles.dto.auth;

import es.daw.simulacro.mvc.mvc_hoteles.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegisterResponseDto {
    private String username;
    private String fullName;
    private String password;
    private String email;
    private Role role;
}
