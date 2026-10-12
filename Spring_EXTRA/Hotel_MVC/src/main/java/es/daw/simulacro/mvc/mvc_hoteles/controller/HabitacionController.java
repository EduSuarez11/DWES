package es.daw.simulacro.mvc.mvc_hoteles.controller;


import es.daw.simulacro.mvc.mvc_hoteles.dto.HabitacionDetailDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/habitaciones")
@Slf4j
public class HabitacionController {

    private final es.daw.simulacro.mvc.mvc_hoteles.service.HabitacionService habitacionService;

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

}
