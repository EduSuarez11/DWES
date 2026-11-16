package es.daw.hotel_api.dto.reserva;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateReservaRequestDto(
        LocalDate fechaEntrada,
        LocalDate fechaSalida,
        BigDecimal precioTotal,
        String fullName,
        String codigoHabitacion
) {}
