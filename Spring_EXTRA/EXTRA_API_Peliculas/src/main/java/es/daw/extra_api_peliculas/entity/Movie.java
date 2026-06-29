package es.daw.extra_api_peliculas.entity;

import es.daw.extra_api_peliculas.enums.GenreType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Entidad que representa una película. LADO INVERSO DE LA RELACIÓN MANY2MANY
 *
 * Relación con MovieCast:
 *   - Una película puede tener varios roles (cast): @OneToMany
 *   - mappedBy = "id.movie" apunta al campo "movie" dentro de la PK compuesta MovieCastId
 *   - cascade ALL + orphanRemoval: si se elimina un MovieCast de la colección,
 *     se borra su fila en movie_cast (Prueba 8)
 */
@Entity
@Table(name = "movies")
@Getter
@Setter
public class Movie {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String title;

    @Column(nullable = false)
    private int releaseYear;

    // Pasarlo a un tipo enumerado
    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private GenreType genre;

    @Column(nullable = false)
    private boolean active = true;

    /**
     * Colección de roles asociados a esta película.
     *
     * - CASCADE: El ciclo de vida del rol lo gestiona Movie, no Actor
     * - Se inicializa como HashSet para evitar NullPointerException al añadir roles.
     * - orphanRemoval = true: al eliminar un MovieCast de este Set y hacer flush,
     *      Hibernate borra la fila en movie_cast sin tocar actors ni movies.
     *      Al hacer delete en el repositorio solo se borra la fila de movie_cast, nunca el actor ni la película
     */
    // combinación de cascde all + orpahnRemoval: si se elimina un MovieCast de la colección
    // se borrar su fila en movie_cast (Prueba 8)
    // Si se elimina una película se borran los MovieCast con id de dicha película
    // Si queda un movieCast con movie a null se borra.
    @OneToMany(mappedBy = "movie",
                cascade = CascadeType.ALL,
                orphanRemoval = true)
    private Set<MovieCast> movieCast = new HashSet<>();


    // -------------------- FASE II --------------------
    /**
     * RELACIÓN OneToMany → Release  (lado inverso)
     * Una película puede estrenarse en múltiples países
     * por distintas distribuidoras (un Release por cada una).
     * mappedBy apunta al campo "movie" que hay dentro de Release.
     * LAZY para no cargar todos los estrenos cada vez que
     * se consulte una película — podrían ser muchos.
     */
    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private List<Release> releases = new ArrayList<>();

    // ---------- Métodos helpers bidireccionales ------------
    public void addMovieCast(MovieCast movieCast){
        this.movieCast.add(movieCast);
        movieCast.setMovie(this);
    }

    public void removeMovieCast(MovieCast movieCast){
        this.movieCast.remove(movieCast);
        movieCast.setMovie(null);
    }

}
