package es.daw.simulacro.mvc.mvc_hoteles.dto;

import java.math.BigDecimal;

public record HabitacionResponseDto(
   String codigo,
   int tamano,
   boolean doble,
   BigDecimal precioNoche,
   boolean incluyeDesayuno,
   boolean ocupada,
   String codigoHotel
) {}
