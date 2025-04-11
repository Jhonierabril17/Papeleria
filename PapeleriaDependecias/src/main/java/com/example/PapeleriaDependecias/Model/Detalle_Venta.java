package com.example.PapeleriaDependecias.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detalle_venta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Detalle_Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detalle;

    private Integer cantidad;
    private Double precio_unitario;

    @ManyToOne
    @JoinColumn(name = "id_venta")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @Override
    public String toString() {
        return "Detalle_Venta{" +
                "id_detalle=" + id_detalle +
                ", cantidad=" + cantidad +
                ", precio_unitario=" + precio_unitario +
                '}';
    }
}

