package es.daw.extra_api_peliculas.repository;

import es.daw.extra_api_peliculas.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}

