package com.coderhouse.proyectofinal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity(name = "producto")
@Table(name = "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;

    @Column(name= "nombre")
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Column(name = "descripcion")
    @NotBlank(message = "La descripcion es obligatoria")
    private String descripcion;

    @Column(name= "precio")
    @NotNull(message = "El precio es obligatorio")
    private Double precio;

    @Column (name = "stock")
    @Min(value = 0, message = "El stock no puede ser menor a 0")
    @NotNull(message = "El stock es obligatorio")
    private Integer stock;


}

