package com.coderhouse.proyectofinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private int stock;
}
