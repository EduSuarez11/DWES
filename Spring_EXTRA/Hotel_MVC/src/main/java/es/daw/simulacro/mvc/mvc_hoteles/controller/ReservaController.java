package es.daw.simulacro.mvc.mvc_hoteles.controller;


import es.daw.simulacro.mvc.mvc_hoteles.dto.reserva.CreateReservaRequestDto;
import es.daw.simulacro.mvc.mvc_hoteles.service.ReservaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reserva")
@RequiredArgsConstructor
@Slf4j
public class ReservaController {

    private final ReservaService reservaService;

    @PostMapping()
    public String reserva(
            @ModelAttribute CreateReservaRequestDto dto
    ) {
        log.info("Fecha entrada: {}", dto.fechaEntrada());
        log.info("Fecha salida: {}", dto.fechaSalida());

        reservaService.createReservationRequest(dto);

        return "reserva/crearReserva";
    }

}
