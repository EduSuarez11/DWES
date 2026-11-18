package es.daw.hotel_api.service;

import es.daw.hotel_api.dto.auth.AuthRequest;
import es.daw.hotel_api.dto.auth.AuthResponse;
import es.daw.hotel_api.dto.auth.register.RegisterRequestDto;
import es.daw.hotel_api.dto.auth.register.RegisterResponseDto;
import es.daw.hotel_api.dto.auth.users.AuthUserResponseDto;
import es.daw.hotel_api.entity.AppUser;
import es.daw.hotel_api.exception.UsernameExistsException;
import es.daw.hotel_api.mapper.AuthMapper;
import es.daw.hotel_api.repository.AppUserRepository;
import es.daw.hotel_api.security.JwtService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AppUserRepository appUserRepository;
    private final AuthMapper authMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /// Registro - 201
    @Transactional
    public RegisterResponseDto registerUser(RegisterRequestDto dto) {
        Optional<AppUser> appUser = appUserRepository.findByEmail(dto.email());

        if (appUser.isPresent()) {
            throw new UsernameExistsException("Ese email ya existe. Por favor, elija otro.");
        }

        AppUser savedUser = appUserRepository.save(authMapper.toAppUserEntity(dto));

        return AuthMapper.toRegisterResponseDto(savedUser);
    }

    /// Buscar todos los usuarios
    @Transactional(readOnly = true)
    public List<AuthUserResponseDto> findAllUsers() {
        List<AppUser> users = appUserRepository.findAll();

        return users.stream()
                .map(AuthMapper::toAuthUserResponseDto)
                .toList();
    }

    public AuthResponse login(AuthRequest dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.username(), dto.password())
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails);
        return new AuthResponse(token);
    }

}
