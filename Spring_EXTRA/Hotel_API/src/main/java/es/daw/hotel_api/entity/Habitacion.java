package es.daw.hotel_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "habitacion")
@AllArgsConstructor @NoArgsConstructor
@Getter
@Setter
public class Habitacion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(nullable = false)
    private int tamano;

    @Column(nullable = false)
    private boolean doble;

    @Column(nullable = false)
    private BigDecimal precioNoche;

    @Column(nullable = false)
    private boolean incluyeDesayuno;

    @Column(nullable = false)
    private boolean ocupada = false;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

}
