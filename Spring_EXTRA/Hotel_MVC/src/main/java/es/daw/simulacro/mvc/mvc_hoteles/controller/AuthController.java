package es.daw.simulacro.mvc.mvc_hoteles.controller;


import es.daw.simulacro.mvc.mvc_hoteles.dto.auth.RegisterRequestDto;
import es.daw.simulacro.mvc.mvc_hoteles.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("userRegister", new RegisterRequestDto());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute("user") RegisterRequestDto dto,
            BindingResult bindingResult,
            RedirectAttributes ra
    ){
        try {
            if (bindingResult.hasErrors()) {
                return "auth/register";
            }

            authService.registerUser(dto);

            ra.addFlashAttribute("message", "Usuario registrado correctamente.");
            return "redirect:/login";
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al registrar el usuario");
            return "redirect:/auth/register";
        }
    }


}
