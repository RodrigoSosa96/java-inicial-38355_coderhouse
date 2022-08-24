package com.coderhouse.proyectofinal.dto;

import com.coderhouse.proyectofinal.entity.Producto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoUpdateDto {
    private Long id;
    private String operacion = "RESTAR";
    private Integer cantidad;
    private Producto producto;
}
