package com.coderhouse.proyectofinal.controller;

import com.coderhouse.proyectofinal.dto.FacturaDto;
import com.coderhouse.proyectofinal.entity.Factura;
import com.coderhouse.proyectofinal.exception.ErrorResponse;
import com.coderhouse.proyectofinal.exception.ProductoException;
import com.coderhouse.proyectofinal.service.FacturaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/factura")
public class FacturaController {
    private final FacturaService facturaService;
    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @PostMapping("/crear")
    public ResponseEntity<Factura> getFactura(@RequestBody FacturaDto facturaDto) {
        Factura factura = facturaService.createFactura(facturaDto);

        return ResponseEntity.ok(factura);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> getFactura(@PathVariable Long id) {
        Factura factura = facturaService.getFacturaById(id);

        return ResponseEntity.ok(factura);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ProductoException.class)
    public ErrorResponse handleProductoException(ProductoException ex) {
//        log.info("ProductoException: {}", ex.getCause().getMessage());
        return new ErrorResponse(LocalDateTime.now(), ex.getMessage(), null , ex.getErrorResponse());
    }
}
