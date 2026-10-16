package es.daw.hotel_api.controller;

import es.daw.hotel_api.dto.habitacion.HabitacionRequestDto;
import es.daw.hotel_api.dto.habitacion.HabitacionResponseDto;
import es.daw.hotel_api.service.HabitacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/habitaciones")
public class HabitacionController {

    private final HabitacionService habitacionService;

    /**
     * Endpoint para buscar las habitaciones según su filtro
     * @param codHotel
     * @param tamanoMinimo
     * @param precioMinimo
     * @param precioMaximo
     * @return
     */
    @GetMapping("/{codigoHotel}/buscar")
    public ResponseEntity<List<HabitacionResponseDto>> searchRoom(
            @PathVariable("codigoHotel") String codHotel,
            @RequestParam(required = false) Integer tamanoMinimo,
            @RequestParam(required = false) Integer precioMinimo,
            @RequestParam(required = false) Integer precioMaximo
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(habitacionService.searchRooms(codHotel, tamanoMinimo, precioMinimo, precioMaximo));
    }

    /**
     * Endpoint para registrar una habitacion de un hotel - 200 Ok
     * @param dto
     * @param codHotel
     * @return
     */
    @PostMapping("/{codigoHotel}")
    public ResponseEntity<HabitacionResponseDto> assignRoomToHotel(
            @RequestBody HabitacionRequestDto dto,
            @PathVariable("codigoHotel") String codHotel
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(habitacionService.registerRoom(dto, codHotel));
    }

    /**
     * Endpoint para eliminar una habitacion de un hotel - 204 No Content
     * @param codigoHabitacion
     * @return
     */
    @DeleteMapping("/{codigoHabitacion}")
    public ResponseEntity<Void> deleteRoom(
            @PathVariable("codigoHabitacion") String codigoHabitacion
    ) {
        habitacionService.deleteRoom(codigoHabitacion);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Endpoint para ocupar una habitacion - 200 Ok
     */
    @PatchMapping("/ocupar/{codigoHabitacion}")
    public ResponseEntity<List<HabitacionResponseDto>> occupyRoom(
            @PathVariable("codigoHabitacion") String codigoHab
            //@RequestBody HotelCodigoRequestDto codigoHotel
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(habitacionService.occupyRoom(codigoHab));
    }


    /**
     * Endpoint para obtener las habitaciones ocupadas
     * @return
     */
    @GetMapping("/reservadas")
    public ResponseEntity<List<HabitacionResponseDto>> getOccupiedRooms() {
        return ResponseEntity.status(HttpStatus.OK).body(habitacionService.getOccupiedRooms());
    }
}

