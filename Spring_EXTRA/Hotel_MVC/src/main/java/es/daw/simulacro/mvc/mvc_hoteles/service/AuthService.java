package es.daw.simulacro.mvc.mvc_hoteles.service;


import es.daw.simulacro.mvc.mvc_hoteles.dto.AuthResponseDto;
import es.daw.simulacro.mvc.mvc_hoteles.dto.UserRequestDto;
import es.daw.simulacro.mvc.mvc_hoteles.exception.ConnectApiRestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final WebClient webClient;

    public String getToken(UserRequestDto dto) {
        AuthResponseDto response = webClient.post()
                .uri("/auth/login")
                .header("Content-Type", "application/json")
                .bodyValue(dto)
                .retrieve()
                .onStatus(HttpStatusCode::isError, r ->
                        r.bodyToMono(AuthResponseDto.class)
                                .flatMap(body -> Mono.error(new ConnectApiRestException("Error: " + r.statusCode() + " " + body)))
                )
                .bodyToMono(AuthResponseDto.class)
                .onErrorMap(ex -> new ConnectApiRestException(ex.getMessage()))
                .block();

        return response != null ? response.getToken() : null;
    }





}
