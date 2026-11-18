package es.daw.hotel_api.mapper;

import es.daw.hotel_api.dto.reserva.CreateReservaResponseDto;
import es.daw.hotel_api.entity.Reserva;
import org.springframework.stereotype.Component;

@Component
public class ReservaMapper {

    public static CreateReservaResponseDto toCreateReservaResponseDto(Reserva reserva) {
        return new CreateReservaResponseDto(
                reserva.getFechaEntrada(),
                reserva.getFechaSalida(),
                reserva.getUser().getEmail()
        );
    }
}
