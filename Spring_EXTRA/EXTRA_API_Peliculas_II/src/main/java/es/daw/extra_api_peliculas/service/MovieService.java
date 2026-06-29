package es.daw.extra_api_peliculas.service;

import es.daw.extra_api_peliculas.dto.response.ActorCastDto;
import es.daw.extra_api_peliculas.dto.response.MovieResponseDto;
import es.daw.extra_api_peliculas.dto.response.MovieWithCastDto;
import es.daw.extra_api_peliculas.entity.Movie;
import es.daw.extra_api_peliculas.entity.MovieCastId;
import es.daw.extra_api_peliculas.enums.Genre;
import es.daw.extra_api_peliculas.exception.ResourceNotFoundException;
import es.daw.extra_api_peliculas.mapper.MovieMapper;
import es.daw.extra_api_peliculas.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;


    public List<MovieResponseDto> getAllMovies(){
        List<MovieResponseDto> movies = movieRepository.findAll()
                .stream()
                .map(movieMapper::toMovieResponseDto)
                .toList();

        return movies;
    }

    public MovieResponseDto getMovieById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));

        return movieMapper.toMovieResponseDto(movie);
    }

    public MovieWithCastDto getMovieByIdWithCast(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));

        List<ActorCastDto> casts = movieRepository.findMovieWithCast(id);

        return new MovieWithCastDto(
                movie.getId(),
                movie.getTitle(),
                movie.getReleaseYear(),
                movie.getGenre(),
                movie.isActive(),
                casts
        );
    }


    public List<MovieWithCastDto>  getAllMoviesWithCast(){
        //return movieRepository.findAll()
        return movieRepository.findAllWithCast()
                .stream()
                .map(movieMapper::toMovieWithCastDto)
                //.collect(Collectors.toList());
                .toList();
    }

    public Page<MovieResponseDto> getMovieWithCastByGenre(Genre genre, Pageable pageable) {
        Page<Movie> movies = (genre == null) ? movieRepository.findAll(pageable) : movieRepository.findMovieByGenre(genre, pageable);
        if (movies == null || movies.isEmpty()) throw new ResourceNotFoundException("Movie not found");
        return movies.map(movieMapper::toMovieResponseDto);
    }

}
