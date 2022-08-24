package com.coderhouse.proyectofinal.dto;

import com.coderhouse.proyectofinal.entity.Producto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckArrayProductosDto {
    private String status;
    private List<String> errorResponse;
    private Iterable<Producto> productos;
}
