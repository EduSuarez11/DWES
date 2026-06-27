package es.daw.extra_estudiantes_mvc.service;


import es.daw.extra_estudiantes_mvc.dto.auth.AuthRequest;
import es.daw.extra_estudiantes_mvc.dto.auth.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final WebClient webClientAuth;

    @Value("${api.estudiantes.auth.login}")
    private String login;
    @Value("${api.estudiantes.auth.password}")
    private String password;

    public String obtenerToken() {
        AuthRequest authRequest = new AuthRequest(login, password);
        AuthResponse response = webClientAuth
                .post()
                .header("Content-Type", "application/json")
                .bodyValue(authRequest)
                .retrieve()
                .bodyToMono(AuthResponse.class)
                .block();

        // pendiente .onStatus y lanzar ConnectionApiRestException
        return response != null ? response.token() : "";
    }

}

