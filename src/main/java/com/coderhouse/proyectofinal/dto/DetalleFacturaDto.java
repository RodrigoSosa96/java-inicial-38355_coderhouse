package com.coderhouse.proyectofinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleFacturaDto {
    DetalleProductoDto producto;
    Integer cantidad;
    Double totalParcial;
}
