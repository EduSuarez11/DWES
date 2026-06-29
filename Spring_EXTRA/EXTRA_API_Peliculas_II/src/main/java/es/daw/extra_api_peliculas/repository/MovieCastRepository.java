package es.daw.extra_api_peliculas.repository;

import es.daw.extra_api_peliculas.entity.MovieCast;
import es.daw.extra_api_peliculas.entity.MovieCastId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieCastRepository extends JpaRepository<MovieCast, MovieCastId> {}