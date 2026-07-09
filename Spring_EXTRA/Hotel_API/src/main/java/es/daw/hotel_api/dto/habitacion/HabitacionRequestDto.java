package es.daw.hotel_api.dto.habitacion;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record HabitacionRequestDto(
        @NotNull(message = "El código de habitación no debe ser nulo")
        String codigo,
        @Min(value = 10, message = "El tamaño de la habitación debe ser mayor de 10 metros cuadrados")
        int tamano,
        boolean doble,
        @Min(message = "El precio de la noche debe ser mayor a 0", value = 0)
        BigDecimal precioNoche,
        boolean incluyeDesayuno
) {}
