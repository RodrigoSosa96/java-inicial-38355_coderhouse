package com.coderhouse.proyectofinal.service;

import com.coderhouse.proyectofinal.dto.request.RequestFacturaDto;
import com.coderhouse.proyectofinal.entity.Factura;

public interface FacturaService {
    Factura getFacturaById(Long id);
    Factura saveFactura(Factura factura);
    Factura createFactura(RequestFacturaDto requestFacturaDto);
}
