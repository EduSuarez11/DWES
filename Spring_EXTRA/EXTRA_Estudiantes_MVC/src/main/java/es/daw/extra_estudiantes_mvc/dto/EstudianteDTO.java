package es.daw.extra_estudiantes_mvc.dto;

public record EstudianteDTO(
        String nia,
        String nombre,
        String primerApellido,
        String segundoApellido,
        String email,
        String movil,
        String direccion,
        String fechaNacimiento,
        int edad
) {
}
