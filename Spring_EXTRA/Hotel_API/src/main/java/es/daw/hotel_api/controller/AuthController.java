package es.daw.hotel_api.controller;


import es.daw.hotel_api.dto.auth.AuthRequest;
import es.daw.hotel_api.dto.auth.AuthResponse;
import es.daw.hotel_api.entity.AppUser;
import es.daw.hotel_api.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {

//    private final JwtService jwtService;
//    private final AuthenticationManager authenticationManager;

//    @PostMapping()
//    public ResponseEntity<AuthResponse> auth(
//            @RequestBody AuthRequest authRequest
//    ) {
//        Authentication a = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password())
//        );
//
//        UserDetails userDetails = (UserDetails) a.getPrincipal();
//
//        if (userDetails == null) return null;
//
//        return ResponseEntity.status(HttpStatus.OK).body(new AuthResponse(jwtService.generateToken(userDetails)));
//    }
}
