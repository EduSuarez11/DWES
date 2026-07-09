package es.daw.hotel_api.mapper;

import es.daw.hotel_api.dto.CategoriaResponseDto;
import es.daw.hotel_api.dto.hotel.HotelResponseDto;
import es.daw.hotel_api.entity.Hotel;
import org.springframework.stereotype.Component;

@Component
public class HotelMapper {

    public static HotelResponseDto hotelToHotelResponseDto(Hotel hotel) {
        return new HotelResponseDto(
                hotel.getCodigo(),
                hotel.getNombre(),
                hotel.getDescripcion(),
                hotel.isPiscina(),
                hotel.getLocalidad(),
                new CategoriaResponseDto(hotel.getCategoria().getCodigo(), hotel.getCategoria().getNombre())
        );
    }
}
