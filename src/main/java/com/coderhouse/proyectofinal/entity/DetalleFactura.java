package com.coderhouse.proyectofinal.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "detalle_factura")
@Table(name = "detalle_factura")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DetalleFactura implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "nombre_producto")
    private String nombreProducto;

    @Column(name= "cantidad")
    private Integer cantidad;

    @Column(name= "precio_unitario")
    private Double precioUnitario;

    @ManyToOne
    @JoinColumn(name ="factura_id")
    @JsonBackReference
    private Factura factura;



}
