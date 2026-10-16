package es.daw.simulacro.mvc.mvc_hoteles.controller;


import es.daw.simulacro.mvc.mvc_hoteles.dto.HabitacionDetailDto;
import es.daw.simulacro.mvc.mvc_hoteles.dto.HabitacionResponseDto;
import es.daw.simulacro.mvc.mvc_hoteles.service.HabitacionService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/habitaciones")
@Slf4j
public class HabitacionController {

    private final HabitacionService habitacionService;

    @GetMapping("/{codigo}/buscar")
    public String viewHabitaciones(
            @PathVariable("codigo") String codigoHab,
            Model model
    ) {
        model.addAttribute("habitaciones", habitacionService.getRoomByCode(codigoHab));
        return "verHabitaciones";
    }


    @PostMapping("/eliminar")
    public String removeRoom(
            @ModelAttribute HabitacionDetailDto dto,
            RedirectAttributes ra
    ) {
        log.info("Codigo Habitacion: {}", dto.codigo());
        log.info("Codigo Hotel: {}", dto.hotelCodigo());
        habitacionService.removeRoomByCode(dto.codigo());
        ra.addFlashAttribute("msg", "Habitación eliminada correctamente");

        return "redirect:/habitaciones/"+ dto.hotelCodigo() + "/buscar";
    }

    @PostMapping("/ocupar")
    public String occupyRoom(
        @ModelAttribute HabitacionDetailDto dto,
        RedirectAttributes ra
    ) {
        try {
            habitacionService.occupyRoomEmpty(dto.codigo(), dto.hotelCodigo());
            ra.addFlashAttribute("msg", "La habitación se ha ocupado correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "No se pudo ocupar la habitación " + dto.codigo());
        }
        return "redirect:/habitaciones/"+ dto.hotelCodigo() + "/buscar";
    }


    @GetMapping("/reservas")
    public String getOccupiedRooms(
            Model model
    ) {
        model.addAttribute("reservas", habitacionService.reqOccupiedRooms());
        return "reservas";
    }
}
