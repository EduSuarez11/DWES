package es.daw.simulacro.mvc.mvc_hoteles.service;



import es.daw.simulacro.mvc.mvc_hoteles.dto.HabitacionResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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
}
