package es.daw.extra_api_peliculas.service;

import es.daw.extra_api_peliculas.entity.Actor;
import es.daw.extra_api_peliculas.exception.ConflictException;
import es.daw.extra_api_peliculas.exception.ResourceNotFoundException;
import es.daw.extra_api_peliculas.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActorService {
    private final ActorRepository actorRepository;


    /// Borrar un actor
    public void deleteActor(Long id) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actor not found with id: " + id));

        if (actor.getMovieCast() != null && !actor.getMovieCast().isEmpty()) throw new ConflictException("Actor is still in a movie");

        actorRepository.delete(actor);
    }
}
