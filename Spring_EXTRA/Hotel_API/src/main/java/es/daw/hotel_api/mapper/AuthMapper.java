package es.daw.hotel_api.mapper;

import es.daw.hotel_api.dto.auth.register.RegisterRequestDto;
import es.daw.hotel_api.dto.auth.register.RegisterResponseDto;
import es.daw.hotel_api.dto.auth.users.AuthUserResponseDto;
import es.daw.hotel_api.entity.AppUser;
import es.daw.hotel_api.entity.Role;
import es.daw.hotel_api.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthMapper {

    private final RoleRepository roleRepository;


    public AppUser toAppUserEntity(RegisterRequestDto dto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Role role = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Rol Inválido"));

        return AppUser.builder()
                .username(dto.username())
                .password(encoder.encode(dto.password()))
                .fullName(dto.fullName())
                .enabled(true)
                .email(dto.email())
                .role(role)
                .build();
    }


    public static RegisterResponseDto toRegisterResponseDto(AppUser user) {
        return new RegisterResponseDto(
                user.getUsername(),
                user.getFullName(),
                user.getPassword(),
                user.getEmail(),
                user.getRole().getName()
        );
    }

    public static AuthUserResponseDto toAuthUserResponseDto(AppUser user) {
        return new AuthUserResponseDto(
                user.getUsername(),
                user.getPassword(),
                user.getFullName(),
                user.getEmail(),
                user.getRole().getName()
        );
    }
}
