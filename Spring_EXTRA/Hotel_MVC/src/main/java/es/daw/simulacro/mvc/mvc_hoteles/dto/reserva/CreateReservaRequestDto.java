package es.daw.simulacro.mvc.mvc_hoteles.dto.reserva;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateReservaRequestDto(
        @NotNull( message = "Debes añadir una fecha de entrada")
        @NotBlank(message = "La fecha de entrada no puede quedar vacía")
        LocalDate fechaEntrada,
        @NotNull( message = "Debes añadir una fecha de salida")
        @NotBlank(message = "La fecha de salida no puede quedar vacía")
        LocalDate fechaSalida,
        String fullName,
        String codigoHabitacion
) {
}
