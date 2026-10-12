package es.daw.simulacro.mvc.mvc_hoteles.controller;



import es.daw.simulacro.mvc.mvc_hoteles.entity.AppUser;
import es.daw.simulacro.mvc.mvc_hoteles.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hoteles")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("/buscar")
    public String viewHotels(
            Model model
    ) {
        model.addAttribute("hoteles", hotelService.getHotels());
        return "buscarHoteles";
    }


}
