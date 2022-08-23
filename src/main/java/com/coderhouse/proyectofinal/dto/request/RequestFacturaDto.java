package com.coderhouse.proyectofinal.dto.request;

import com.coderhouse.proyectofinal.dto.ProductoUpdateDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestFacturaDto {
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Argentina/Buenos_Aires")
    private LocalDateTime fecha = LocalDateTime.now();
    private Long clienteId;
    private List<ProductoUpdateDto> productos;

    private Long empresaId = 1L;

    public List<Long> getProductosIds() {
        return productos
                .stream()
                .map(ProductoUpdateDto::getId)
                .collect(Collectors.toList());
    }
}
