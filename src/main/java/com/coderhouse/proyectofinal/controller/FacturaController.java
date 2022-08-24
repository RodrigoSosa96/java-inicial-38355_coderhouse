package com.coderhouse.proyectofinal.controller;

import com.coderhouse.proyectofinal.dto.FacturaDto;
import com.coderhouse.proyectofinal.entity.Factura;
import com.coderhouse.proyectofinal.service.FacturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
