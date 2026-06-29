package es.daw.extra_api_peliculas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


/**
 * Tabla intermedia entre Movie y Actor con atributos propios. LADO PROPIETARIO DE LA RELACIÓN MANY2MANY
 *
 * Resuelve la relación Many-to-Many entre películas y actores de forma
 * explícita porque la relación tiene datos propios:
 *   - characterName: nombre del personaje interpretado
 *   - screenMinutes: minutos en pantalla
 *   - salaryOverride: salario negociado (puede ser null)
 *   - active: estado del rol
 *
 * PK compuesta (movie_id, actor_id):
 *   - Garantiza que un actor no puede tener dos roles en la misma película.
 *   - @EmbeddedId + @MapsId delegan la gestión de las FK en las relaciones
 *     @ManyToOne con Movie y Actor.
 *
 * Por qué NO se usa @ManyToMany:
 *   - La tabla intermedia tiene atributos propios que @ManyToMany no puede mapear.
 *   - Otras entidades podrían necesitar apuntar a MovieCast en el futuro.
 */
@Entity
@Table(name = "movie_cast")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieCast {

    // PK compuesta
    @EmbeddedId
    private MovieCastId id;

    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("actorId")
    @JoinColumn(name = "actor_id")
    private Actor actor;




    // propiedades propias de la tabla intermedia
    @Column(nullable = false, length = 80)
    private String characterName;

    @Column(nullable = false)
    private short screenMinutes;

    @Column(precision = 12, scale = 2)
    private BigDecimal salaryOverride;

    private boolean active;





}
