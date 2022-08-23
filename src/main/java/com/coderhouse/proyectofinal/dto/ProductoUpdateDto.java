package com.coderhouse.proyectofinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoUpdateDto {
    private Long id;
    private String operacion;
    private Integer cantidad;
}
