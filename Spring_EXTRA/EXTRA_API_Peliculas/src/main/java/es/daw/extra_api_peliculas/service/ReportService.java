package es.daw.extra_api_peliculas.service;


import es.daw.extra_api_peliculas.dto.response.TopGrossingMovieReport;
import es.daw.extra_api_peliculas.repository.BoxOfficeEntryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ReportService {

    private final BoxOfficeEntryRepository boxOfficeEntryRepository;

    public List<TopGrossingMovieReport> getTopGrossingMovies(String genre, LocalDate from, LocalDate to, Pageable pageable) {

        if (from != null && to != null && from.isAfter(to)) {
            throw new RuntimeException("La fecha 'from' (%s) debe ser anterior a la fecha 'to' (%s)".formatted(from, to));
        }

        List<TopGrossingMovieReport> result = boxOfficeEntryRepository.topGrossingMovies(genre, from, to, pageable);

        return result;
    }


}
