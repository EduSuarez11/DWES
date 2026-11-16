package es.daw.hotel_api.controller;


import es.daw.hotel_api.dto.reserva.ReservasUsuarioRequestDto;
import es.daw.hotel_api.dto.reserva.ReservasUsuarioResponseDto;
import es.daw.hotel_api.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserva")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    @GetMapping("/view/user")
    public ResponseEntity<List<ReservasUsuarioResponseDto>> getReservationsOfUser(
            @RequestBody ReservasUsuarioRequestDto dto
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(reservaService.getReservationsForUser(dto));
    }

    @PostMapping("/create")
    public ResponseEntity<ReservasUsuarioResponseDto> reserveRoom() {
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

}
