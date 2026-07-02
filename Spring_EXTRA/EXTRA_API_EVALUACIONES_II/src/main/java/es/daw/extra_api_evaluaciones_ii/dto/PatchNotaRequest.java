package es.daw.extra_api_evaluaciones_ii.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

public record PatchNotaRequest(
        Integer calificacion
) {}
