package es.daw.hotel_api.dto.reserva;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ReservasUsuarioResponseDto(
         LocalDate fechaEntrada,
         LocalDate fechaSalida,
         BigDecimal precioTotal,
         LocalDateTime fechaCreacion,
         String fullName,
         String codigoHabitacion

) {}
