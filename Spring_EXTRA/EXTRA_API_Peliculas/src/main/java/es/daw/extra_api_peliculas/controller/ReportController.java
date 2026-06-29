package es.daw.extra_api_peliculas.controller;

import es.daw.extra_api_peliculas.dto.response.TopGrossingMovieReport;
import es.daw.extra_api_peliculas.repository.BoxOfficeEntryRepository;
import es.daw.extra_api_peliculas.service.ReportService;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    // Público — cualquiera puede consultar el report
    @GetMapping("/top-grossing")
    public List<TopGrossingMovieReport> topGrossing(
            @RequestParam(required = false) @Pattern(regexp = "^\\b(ACTION|WAR|SCI_FI|DRAMA)\\b$") String genre,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate from,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate to,

            @PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
        return reportService.getTopGrossingMovies(genre, from, to, pageable);
    }
}
