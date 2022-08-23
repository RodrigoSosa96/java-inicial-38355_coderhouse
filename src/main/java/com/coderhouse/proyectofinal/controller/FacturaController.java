package com.coderhouse.proyectofinal.controller;

import com.coderhouse.proyectofinal.dto.request.RequestFacturaDto;
import com.coderhouse.proyectofinal.entity.Factura;
import com.coderhouse.proyectofinal.service.FacturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/factura")
public class FacturaController {
    private final FacturaService facturaService;
    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @PostMapping("/crear")
    public ResponseEntity<Factura> getFactura(@RequestBody RequestFacturaDto requestFacturaDto) {
        Factura factura = facturaService.createFactura(requestFacturaDto);

        return ResponseEntity.ok(factura);
    }
}
