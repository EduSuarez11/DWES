package es.daw.hotel_api.controller;


import es.daw.hotel_api.dto.reserva.CreateReservaRequestDto;
import es.daw.hotel_api.dto.reserva.CreateReservaResponseDto;
import es.daw.hotel_api.dto.reserva.ReservasUsuarioRequestDto;
import es.daw.hotel_api.dto.reserva.ReservasUsuarioResponseDto;
import es.daw.hotel_api.entity.AppUser;
import es.daw.hotel_api.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserva")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    /**
     * Endpoint para consultar las reservas de un usuario - 200 OK
     * @param user
     * @return
     */
    @GetMapping("/user/list")
    public ResponseEntity<List<ReservasUsuarioResponseDto>> getReservationsOfUser(
            @AuthenticationPrincipal AppUser user
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(reservaService.getReservationsForUser(user.getId()));
    }

    /**
     * Endpoint para crear una reserva de una habitacion - 201 CREATED
     * @param dto
     * @param codigoHabitacion
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<CreateReservaResponseDto> reserveRoom(
            @RequestBody CreateReservaRequestDto dto,
            @RequestParam("codigoHabitacion") String codigoHabitacion
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.createReservation(dto, codigoHabitacion));
    }

}
