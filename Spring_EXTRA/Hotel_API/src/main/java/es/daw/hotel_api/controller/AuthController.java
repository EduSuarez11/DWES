package es.daw.hotel_api.controller;


import es.daw.hotel_api.dto.auth.AuthRequest;
import es.daw.hotel_api.dto.auth.AuthResponse;
import es.daw.hotel_api.dto.auth.register.RegisterRequestDto;
import es.daw.hotel_api.dto.auth.register.RegisterResponseDto;
import es.daw.hotel_api.dto.auth.users.AuthUserResponseDto;
import es.daw.hotel_api.entity.AppUser;
import es.daw.hotel_api.security.JwtService;
import es.daw.hotel_api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody AuthRequest authRequest
    ) {
        Authentication a = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password())
        );

        UserDetails userDetails = (UserDetails) a.getPrincipal();

        if (userDetails == null) return null;

        return ResponseEntity.status(HttpStatus.OK).body(new AuthResponse(jwtService.generateToken(userDetails)));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(
            @RequestBody RegisterRequestDto dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registerUser(dto));
    }

    @GetMapping("/users")
    public ResponseEntity<List<AuthUserResponseDto>> findAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(authService.findAllUsers());
    }


}
