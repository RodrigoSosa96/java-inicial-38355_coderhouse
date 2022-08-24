package com.coderhouse.proyectofinal.dto;

import com.coderhouse.proyectofinal.entity.Cliente;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FacturaDto {
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Argentina/Buenos_Aires")
    private LocalDateTime fecha = LocalDateTime.now();

    private Long clienteId;

    private Cliente cliente;

    private List<ProductoUpdateDto> productos;

    private Long empresaId = 1L;

    public List<Long> getProductosIds() {
        return productos
                .stream()
                .map(ProductoUpdateDto::getId)
                .collect(Collectors.toList());
    }
}
