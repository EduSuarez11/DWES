package es.daw.hotel_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "categoria")
@AllArgsConstructor @NoArgsConstructor
@Getter
@Setter
public class Categoria {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    // ------------- Relaciones -----------------
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Hotel> hotels;

    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
        hotel.setCategoria(this);
    }

    public void removeHotel(Hotel hotel) {
        hotels.remove(hotel);
        hotel.setCategoria(null);
    }
}
