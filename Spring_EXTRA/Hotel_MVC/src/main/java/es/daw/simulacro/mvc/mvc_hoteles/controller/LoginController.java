package es.daw.simulacro.mvc.mvc_hoteles.controller;


import es.daw.simulacro.mvc.mvc_hoteles.dto.HotelResponseDto;
import es.daw.simulacro.mvc.mvc_hoteles.dto.UserRequestDto;
import es.daw.simulacro.mvc.mvc_hoteles.exception.ConnectApiRestException;
import es.daw.simulacro.mvc.mvc_hoteles.service.AuthService;
import es.daw.simulacro.mvc.mvc_hoteles.service.HotelService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/login")
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final AuthService authService;
    private final HotelService hotelService;


//    @GetMapping()
//    public String login() {
//        return "login";
//    }
////    @PostMapping()
////    public String login(
////            @ModelAttribute("user") UserRequestDto dto,
////            RedirectAttributes ra
////    ) {
////        try {
////            //authService.getToken();
////
////
////        } catch (Exception e) {
////            ra.addFlashAttribute("error", e.getMessage());
////            return "login";
////        }
////
////        return "buscarHoteles";
////    }
//    @PostMapping()
//    public String procesarLogin(@ModelAttribute UserRequestDto dto,
//                                HttpSession session,
//                                Model model) {
//        try {
//            String token = null;
//            log.info("* username: {}", dto.username());
//            log.info("* password: {}", dto.password());
//            if (dto.username() == null || dto.username().isEmpty()) {
//                return "redirect:/login?error";
//            }
//
//            if ("invitado".equalsIgnoreCase(dto.username())) {
//            session.setAttribute("modo", "invitado");
//            session.setAttribute("rol", "INVITADO");
//
//            } else {
//                System.out.println("***************************");
//
//                System.out.println("* username: " + dto.username());
//                System.out.println("* password: " + dto.password());
//                System.out.println("***************************");
//
//                token = authService.getToken(dto);
//                System.out.println("***************************");
//                System.out.println("* token: " + token);
//                System.out.println("***************************");
//            if (token == null || token.isEmpty() || token.startsWith("ERROR")) {
//                model.addAttribute("error", token);
//                return "login";
//            }
//                session.setAttribute("jwt", token);
//                if(dto.username().equalsIgnoreCase("admin"))
//                    session.setAttribute("rol", "ADMIN");
//                else
//                    session.setAttribute("rol", "USER");
//
//
//            }
//
//            // Buscar todos los hoteles sin filtro de ciudad
//            List<HotelResponseDto> hoteles = hotelService.getHotels();
//            model.addAttribute("hoteles", hoteles);
//
//            return "buscarHoteles"; // vista Thymeleaf que muestra resultados
//        } catch (ConnectApiRestException e) {
//            model.addAttribute("error", e.getMessage());
//            return "login";
//        }
//
//    }
}
