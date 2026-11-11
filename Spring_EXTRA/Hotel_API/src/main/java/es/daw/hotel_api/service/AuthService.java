package es.daw.hotel_api.service;

import es.daw.hotel_api.dto.auth.register.RegisterRequestDto;
import es.daw.hotel_api.dto.auth.register.RegisterResponseDto;
import es.daw.hotel_api.dto.auth.users.AuthUserResponseDto;
import es.daw.hotel_api.dto.auth.users.ReservasUsuarioRequestDto;
import es.daw.hotel_api.dto.habitacion.HabitacionResponseDto;
import es.daw.hotel_api.entity.AppUser;
import es.daw.hotel_api.exception.UsernameExistsException;
import es.daw.hotel_api.mapper.AuthMapper;
import es.daw.hotel_api.repository.AppUserRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AppUserRepository appUserRepository;
    private final AuthMapper authMapper;

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

    @Transactional
    public List<HabitacionResponseDto> getReservationsOfUser(ReservasUsuarioRequestDto dto) {
        AppUser user = appUserRepository.findByUsername(dto.username())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));



        return null;
    }
}
