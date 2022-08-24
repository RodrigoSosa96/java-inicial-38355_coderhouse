package com.coderhouse.proyectofinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckProductoDto {
    private String status;
    private String nombre;
    private String errorResponse;
    private Integer stock;

}
