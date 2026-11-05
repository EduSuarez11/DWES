package es.daw.simulacro.mvc.mvc_hoteles.service;


import es.daw.simulacro.mvc.mvc_hoteles.dto.auth.RegisterRequestDto;
import es.daw.simulacro.mvc.mvc_hoteles.dto.auth.RegisterResponseDto;
import es.daw.simulacro.mvc.mvc_hoteles.entity.AppUser;
import es.daw.simulacro.mvc.mvc_hoteles.exception.ConnectApiRestException;
import es.daw.simulacro.mvc.mvc_hoteles.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final WebClient webClient;
    private final AppUserRepository appUserRepository;

    public void registerUser(RegisterRequestDto dto) {
        RegisterResponseDto dataUser = webClient.post()
                .uri("/auth/register")
                .header("Content-Type", "application/json")
                .bodyValue(dto)
                .retrieve()
                .onStatus(HttpStatusCode::isError, r ->
                        r.bodyToMono(RegisterResponseDto.class)
                                .flatMap(body -> Mono.error(new ConnectApiRestException("Error: " + r.statusCode() + " " + body)))
                )
                .bodyToMono(RegisterResponseDto.class)
                .onErrorMap(ex -> new ConnectApiRestException(ex.getMessage()))
                .block();

        AppUser user = new AppUser();
        if (dataUser != null) {
            user.setUsername(dataUser.getUsername());
            user.setPassword(dataUser.getPassword());
            user.setEmail(dataUser.getEmail());
            user.setFullName(dataUser.getFullName());
            user.setRole(dataUser.getRole());

            appUserRepository.save(user);
        }

        //return dataUser;
    }





}
