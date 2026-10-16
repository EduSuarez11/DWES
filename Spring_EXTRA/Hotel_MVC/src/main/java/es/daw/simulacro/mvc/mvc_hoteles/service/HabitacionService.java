package es.daw.simulacro.mvc.mvc_hoteles.service;



import es.daw.simulacro.mvc.mvc_hoteles.dto.HabitacionResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitacionService {

    private final WebClient webClient;

    public List<HabitacionResponseDto> getRoomByCode(String codigoHab) {
        HabitacionResponseDto[] habitaciones = webClient.get()
                .uri("/habitaciones/" + codigoHab + "/buscar")
                .retrieve()
                .bodyToMono(HabitacionResponseDto[].class)
                .block();

        return habitaciones != null ? List.of(habitaciones) : List.of();
    }

    public void removeRoomByCode(String codigoHab) {
        webClient.delete()
                .uri("/habitaciones/" + codigoHab)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public List<HabitacionResponseDto> occupyRoomEmpty(String codigoHab, String codigoHotel) {
        HabitacionResponseDto[] habitacionResponseDto = webClient.patch()
                .uri("/habitaciones/ocupar/" + codigoHab)
                .retrieve()
//                .onStatus(HttpStatusCode::is4xxClientError, r ->
//                    r.bodyToMono(HabitacionResponseDto.class)
//                            .flatMap(body -> Mono.error())
//                )
                .bodyToMono(HabitacionResponseDto[].class)
                .block();
        System.out.println("Lista de habitaciones:" + Arrays.toString(habitacionResponseDto));
        return habitacionResponseDto != null ? Arrays.asList(habitacionResponseDto) : Arrays.asList();
    }


    public List<HabitacionResponseDto> reqOccupiedRooms() {
        HabitacionResponseDto[] habitaciones = webClient.get()
                .uri("/habitaciones/reservadas")
                .retrieve()
                .bodyToMono(HabitacionResponseDto[].class)
                .block();

        return habitaciones != null ? List.of(habitaciones) : List.of();
    }

}
