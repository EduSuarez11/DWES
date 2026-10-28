package es.daw.hotel_api.dto.auth.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserResponseDto {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String role;
}
