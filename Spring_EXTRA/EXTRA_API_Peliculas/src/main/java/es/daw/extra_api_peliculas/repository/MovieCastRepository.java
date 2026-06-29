package es.daw.extra_api_peliculas.repository;

import es.daw.extra_api_peliculas.dto.response.ActorResponseDto;
import es.daw.extra_api_peliculas.dto.response.MovieCastResponseDto;
import es.daw.extra_api_peliculas.entity.MovieCast;
import es.daw.extra_api_peliculas.entity.MovieCastId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieCastRepository extends JpaRepository<MovieCast, MovieCastId> {

    @Query("""
    SELECT new es.daw.extra_api_peliculas.dto.response.ActorResponseDto(
        a.id.actorId,
        a.actor.stageName,
        a.actor.fullName,
        a.actor.nationality,
        a.actor.active
    ) FROM Movie m
            JOIN m.movieCast a
            WHERE m.id = :movieId

    """)
    List<ActorResponseDto> getActorsByMovieId(@Param("movieId") Long movieId);

    @Query("""
    SELECT new es.daw.extra_api_peliculas.dto.response.MovieCastResponseDto(
        m.id.movieId,
        m.movie.title,
        m.movie.releaseYear,
        m.movie.genre,
        m.movie.active,
        a.id,
        a.fullName
    ) FROM Actor a
            JOIN a.movieCast m
            WHERE a.id = :actorId

    """)
    List<MovieCastResponseDto> getMoviesByActorId(@Param("actorId") Long actorId);

    MovieCast getMovieCastByMovieIdAndActorId(Long movieId, Long actorId);
}