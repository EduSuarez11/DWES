package es.daw.extra_api_peliculas.repository;

import es.daw.extra_api_peliculas.dto.response.TopGrossingMovieReport;
import es.daw.extra_api_peliculas.entity.BoxOfficeEntry;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDate;
import java.util.List;

public interface BoxOfficeEntryRepository extends JpaRepository<BoxOfficeEntry, Long> {
    @Query("""
    SELECT new es.daw.extra_api_peliculas.dto.response.TopGrossingMovieReport(
        m.id,
        m.title,
        m.genre,
        count(boe.id),
        sum(boe.screens),
        sum(boe.gross)
    )
        FROM BoxOfficeEntry boe
            JOIN boe.release r
            JOIN r.movie m
        WHERE boe.active = true
    AND r.active   = true
    AND m.active   = true
    AND (:genre IS NULL OR m.genre = :genre)
          AND (:from  IS NULL OR boe.periodStart >= :from)
          AND (:to    IS NULL OR boe.periodEnd   <= :to)
    """)
    List<TopGrossingMovieReport> topGrossingMovies(
            @Param("genre") String genre,
            @Param("from")  LocalDate from,
            @Param("to") LocalDate to,
            Pageable pageable
    );
}
