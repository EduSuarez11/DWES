package es.daw.extra_estudiantes_mvc.service;

import es.daw.extra_estudiantes_mvc.config.AppConfig;
import es.daw.extra_estudiantes_mvc.dto.EstudianteDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class FiltrosService {

    // Usar webclient para conectar con la API.
    // Opcion 1: Resuelve por nombre del metodo
    private final WebClient webClientAPI;

    // Opcion 2: Resuelve por la anotacion @Qualifier
    // @Qualifier("webClientAuth")
    // private WebClient webClient

    // Opcion 3: Resuelve si incluyes la anotacion @Primary en la clase de configuracion de cualquier servicio
    // @Autowired
    // private WebClient webClientP;



    private final AuthService authService;

    public EstudianteDTO filtrarEstudiantes(Function<UriBuilder, URI> urlFn) {
        // Primero me autentifico para obtener el token

        String token = authService.obtenerToken();
        log.info("Token: " + token);
        // Despues con el token vuelvo a usar webclient para obtener el informe del estudiante en base al filtro
        return webClientAPI.method(HttpMethod.GET)
                .uri(urlFn)
                .header("Authorization", "Bearer " + token)
                .retrieve()
//                .onStatus(
//                        error -> error.isError(),
//                        response -> {
//                            return response.bodyToMono(String.class).defaultIfEmpty("")
//                                    .flatMap(errorBody -> Mono.error( () -> new RuntimeException("Error HTTP en el endpoint")));
//                            }
//                        )
                .bodyToMono(EstudianteDTO.class)
                .block();
    }


//    public EstudianteDTO findByNia(String nia) {
//        String token = authService.obtenerToken();
//
//        return webClientAPI.method(HttpMethod.GET)
//                .uri(uriBuilder -> uriBuilder
//                        .path("/estudiantes/search/findByNia")
//                        .queryParam("nia", nia)
//                        .build())
//                .header("Authorization", "Bearer " + token)
//                .retrieve()
//                .bodyToMono(EstudianteDTO.class)
//                .block();
//    }


}
