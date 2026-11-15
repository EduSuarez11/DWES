package es.daw.simulacro.mvc.mvc_hoteles.service;


import es.daw.simulacro.mvc.mvc_hoteles.dto.auth.RegisterResponseDto;
import es.daw.simulacro.mvc.mvc_hoteles.dto.reserva.CreateReservaRequestDto;
import es.daw.simulacro.mvc.mvc_hoteles.dto.reserva.CreateReservaResponseDto;
import es.daw.simulacro.mvc.mvc_hoteles.exception.ConnectApiRestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final WebClient webClient;

    public CreateReservaResponseDto createReservationRequest(CreateReservaRequestDto dto) {
        return webClient.post()
                .uri("/reservas/create")
                .bodyValue(dto)
                .retrieve()
                .onStatus(HttpStatusCode::isError, r ->
                        r.bodyToMono(CreateReservaResponseDto.class)
                                .flatMap(body -> Mono.error(new ConnectApiRestException("Error: " + r.statusCode() + " " + body)))
                )
                .bodyToMono(CreateReservaResponseDto.class)
                .block();
    }


}
