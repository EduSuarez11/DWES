package es.daw.hotel_api.mapper;

import es.daw.hotel_api.dto.habitacion.HabitacionResponseDto;
import es.daw.hotel_api.entity.Habitacion;
import org.springframework.stereotype.Component;

@Component
public class HabitacionMapper {

    public static HabitacionResponseDto habitacionToHabitacionResponseDto(Habitacion habitacion) {
        return new HabitacionResponseDto(
                habitacion.getCodigo(),
                habitacion.getTamano(),
                habitacion.isDoble(),
                habitacion.getPrecioNoche(),
                habitacion.isIncluyeDesayuno(),
                habitacion.isOcupada(),
                habitacion.getHotel().getCodigo()
        );
    }
}
