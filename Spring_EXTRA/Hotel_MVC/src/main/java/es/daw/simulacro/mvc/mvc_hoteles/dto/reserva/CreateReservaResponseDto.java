package es.daw.simulacro.mvc.mvc_hoteles.dto.reserva;

import java.time.LocalDate;

public record CreateReservaResponseDto(
        LocalDate fechaEntrada,
        LocalDate fechaSalida,
        String codigoHabitacion
) {}
