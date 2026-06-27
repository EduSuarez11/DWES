package es.daw.extra_estudiantes_mvc.controller;

import es.daw.extra_estudiantes_mvc.dto.EstudianteDTO;
import es.daw.extra_estudiantes_mvc.service.FiltrosService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class FiltrosController {

    private final FiltrosService filtrosService;

    @GetMapping("/filtros")
    public String filtros(
            @RequestParam(required = false) String nia,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String primerApellido,
            @RequestParam(required = false) String segundoApellido,
            Model model
    ) {

        EstudianteDTO estudianteDTO = null;
        if (nia != null) {
            // Una vez que defino en el servicio el parametro de Function<URIBuilder, URI>, ya me proporciona los metodos necesarios para generar la URI
            estudianteDTO = filtrosService.filtrarEstudiantes(
                    uriBuilder -> uriBuilder
                                .path("/findByNia")
                                .queryParam("nia", nia)
                                .build()
            );
        } else if (nombre != null && primerApellido != null && segundoApellido != null) {
            estudianteDTO = filtrosService.filtrarEstudiantes(
                    uriBuilder -> uriBuilder
                            .path("/findByNombreAndPrimerApellidoAndSegundoApellido")
                            .queryParam("nombre", nombre)
                            .queryParam("primerApellido", primerApellido)
                            .queryParam("segundoApellido", segundoApellido)
                            .build()
            );
        }
        model.addAttribute("estudiante", estudianteDTO);

        return "estudiantes/informe";
    }
}
