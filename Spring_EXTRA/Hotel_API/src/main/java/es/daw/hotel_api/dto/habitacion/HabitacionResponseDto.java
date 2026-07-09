package es.daw.hotel_api.dto.habitacion;

import java.math.BigDecimal;

public record HabitacionResponseDto(
   String codigo,
   int tamano,
   boolean doble,
   BigDecimal precioNoche,
   boolean incluyeDesayuno,
   boolean ocupada
) {}
