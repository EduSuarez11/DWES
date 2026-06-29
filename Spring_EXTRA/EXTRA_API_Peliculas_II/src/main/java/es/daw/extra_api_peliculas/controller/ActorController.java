package es.daw.extra_api_peliculas.controller;

import es.daw.extra_api_peliculas.service.MovieCastService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actors")
@RequiredArgsConstructor
public class ActorController {

    // Mejora!!! el servicio debería ser el específico de actor!!!!
    private final MovieCastService movieCastService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable Long id) {
        movieCastService.deleteActor(id);
        return ResponseEntity.noContent().build();
    }
}
