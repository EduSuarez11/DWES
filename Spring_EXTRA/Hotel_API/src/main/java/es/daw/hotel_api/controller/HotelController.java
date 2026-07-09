package es.daw.hotel_api.controller;

import es.daw.hotel_api.dto.hotel.HotelRequestDto;
import es.daw.hotel_api.dto.hotel.HotelResponseDto;
import es.daw.hotel_api.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hoteles")
public class HotelController {

    private final HotelService hotelService;

    /**
     * Endpoint para obtener los hoteles por filtros (codigo de categoria y localidad) - 200 Ok
     * @param codigoCategoria
     * @param localidad
     * @return
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<HotelResponseDto>> getHotels(
            @RequestParam(required = false) String codigoCategoria,
            @RequestParam(required = false) String localidad
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotels(codigoCategoria, localidad));
    }

    /**
     * Endpoint para crear un hotel - 201 Created
     * @return
     */
    @PostMapping()
    public ResponseEntity<HotelResponseDto> createHotel(
            @RequestBody HotelRequestDto hotelDto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(hotelDto));
    }

}
