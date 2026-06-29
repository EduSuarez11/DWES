package es.daw.extra_api_peliculas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/*
 * Clave compuesta de MovieCast
 * Representa el par (movie_id, actor_id) que garantiza que un actor.
 * no puede repetir la misma película (Prueba 4 → 409 Conflict).
 *
 * Requisitos de JPA para @Embeddable como PK:
 *   1. Debe implementar Serializable.
 *   2. Debe sobreescribir equals() y hashCode() correctamente.
 *      Sin esto, la detección de duplicados en el Set<MovieCast> de Movie
 *      no funciona y el 409 no se lanza.
 *      // Object por defecto:
                hashCode() → dirección de memoria del objeto (siempre distinta para objetos distintos)
                equals()   → comparación de referencia (==)
 */

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class MovieCastId implements Serializable {

    @Column(name="movie_id", nullable = false)
    private Long movieId;

    @Column(name="actor_id", nullable = false)
    private Long actorId;



}