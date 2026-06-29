package es.daw.extra_api_peliculas.controller;

import es.daw.extra_api_peliculas.dto.response.MovieCastResponseDto;
import es.daw.extra_api_peliculas.entity.Actor;
import es.daw.extra_api_peliculas.exception.ConflictException;
import es.daw.extra_api_peliculas.exception.ResourceNotFoundException;
import es.daw.extra_api_peliculas.repository.ActorRepository;
import es.daw.extra_api_peliculas.service.ActorService;
import es.daw.extra_api_peliculas.service.MovieCastService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/actors")
@RequiredArgsConstructor
public class ActorController {
    private final MovieCastService movieCastService;
    private final ActorService actorService;

    /**
     * Endpoint que lista las películas en las que participa un actor: 200
     * @param actorId
     * @return
     */
    @GetMapping("/{id}/cast")
    public ResponseEntity<List<MovieCastResponseDto>> listMoviesByActor(
            @PathVariable("id") Long actorId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(movieCastService.listMovies(actorId));
    }


    /**
     * Endpoint que elimina un actor: 204
     *  Comprobar vía java que al borrar un actor, si participa en una película NO BORRARLO!!!
     *  No usar CASCADE en Actor, sino impedir vía java que no se borre...
     * @param actorId
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActor(
            @PathVariable("id") Long actorId
    ) {
        actorService.deleteActor(actorId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
