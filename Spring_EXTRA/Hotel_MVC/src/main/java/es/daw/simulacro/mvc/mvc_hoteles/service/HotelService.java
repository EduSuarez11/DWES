package es.daw.simulacro.mvc.mvc_hoteles.service;



import es.daw.simulacro.mvc.mvc_hoteles.dto.HotelResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final WebClient webClientApi;

    public List<HotelResponseDto> getHotels() {
        HotelResponseDto[] hotels = webClientApi.get()
                .uri("/hoteles/buscar")
                .retrieve()
                .bodyToMono(HotelResponseDto[].class)
                .block();
        return hotels != null ? List.of(hotels) : List.of();
    }
}
