package es.daw.extra_api_peliculas.service;

import es.daw.extra_api_peliculas.dto.response.MovieWithCastDto;
import es.daw.extra_api_peliculas.entity.Movie;
import es.daw.extra_api_peliculas.exception.ResourceNotFoundException;
import es.daw.extra_api_peliculas.mapper.MovieMapper;
import es.daw.extra_api_peliculas.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;


    /// Borrar una pelicula
    public void deleteMovie(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));

        movieRepository.delete(movie);
    }

    /// Listar todas las peliculas con sus actores
    public List<MovieWithCastDto> getAllMoviesWithCast() {

        return movieRepository.findAllWithCast()
                .stream()
                .map(movieMapper::toMovieWithCastDto)
                .toList();
    }

}
