package com.coderhouse.proyectofinal.service;

import com.coderhouse.proyectofinal.api.TimeApi;
import com.coderhouse.proyectofinal.dto.ProductoUpdateDto;
import com.coderhouse.proyectofinal.dto.request.RequestFacturaDto;
import com.coderhouse.proyectofinal.entity.*;
import com.coderhouse.proyectofinal.repository.FacturaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FacturaServiceImpl implements FacturaService {

    private final FacturaRepository facturaRepository;
    private final ClienteService clienteService;
    private final ProductoService productoService;
    private final EmpresaService empresaService;

    private final TimeApi timeApi;

    public FacturaServiceImpl(FacturaRepository facturaRepository, ClienteService clienteService, ProductoService productoService, EmpresaService empresaService, TimeApi timeApi) {
        this.facturaRepository = facturaRepository;
        this.clienteService = clienteService;
        this.productoService = productoService;
        this.empresaService = empresaService;
        this.timeApi = timeApi;
    }

    public Factura getFacturaById(Long id) {
        return facturaRepository.findById(id).orElse(null);
    }

    public Factura createFactura(RequestFacturaDto requestFacturaDto) {
        Cliente cliente = clienteService.getClienteById(requestFacturaDto.getClienteId());
        Empresa empresa = empresaService.getEmpresaById(requestFacturaDto.getEmpresaId());

        Iterable<Producto> productos = productoService.getFromIds(requestFacturaDto.getProductosIds());

        Factura factura = new Factura();

        Optional<Date> fecha = timeApi.getTime();
        if (fecha.isPresent()) {
            factura.setFecha(fecha.get().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime());
        }else {
            factura.setFecha(LocalDateTime.now());
        }
        log.info("Fecha: {}", timeApi.getTime());


        factura.setTipoFactura('A');
        factura.setCliente(cliente);
        factura.setEmpresa(empresa);

        List<ProductoUpdateDto> productosDto = requestFacturaDto.getProductos();
        List<DetalleFactura> detallesFacturas = new ArrayList<>();



        Double total = 0.0;
//        factura.setDetallesFacturas(new ArrayList<>());
        for(ProductoUpdateDto productoDto: productosDto) {
            Long id = productoDto.getId();
            Integer cantidad = productoDto.getCantidad();

            Producto producto = productoService.getProductoById(id);
            productoService.checkStock(producto, cantidad, "restar");

            DetalleFactura detalleFactura = new DetalleFactura();
            DetalleProducto detalleProducto = new DetalleProducto();

            detalleProducto.setNombre(producto.getNombre());
            detalleProducto.setDescripcion(producto.getDescripcion());
            detalleProducto.setPrecioUnitario(producto.getPrecio());


            detalleFactura.setDetalleProducto(detalleProducto);
            detalleFactura.setCantidad(cantidad);

            detalleFactura.setTotalParcial(producto.getPrecio() * cantidad);

            detallesFacturas.add(detalleFactura);
            detalleFactura.setFactura(factura);

            total += detalleFactura.getTotalParcial();
        }

        factura.setDetallesFacturas(detallesFacturas);
        factura.setTotalFactura(total);

        facturaRepository.save(factura);

        return factura;
    }

    public Factura saveFactura(Factura factura) {
        return facturaRepository.save(factura);
    }


}
