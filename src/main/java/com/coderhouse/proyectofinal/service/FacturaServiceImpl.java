package com.coderhouse.proyectofinal.service;

import com.coderhouse.proyectofinal.api.TimeApi;
import com.coderhouse.proyectofinal.dto.FacturaDto;
import com.coderhouse.proyectofinal.dto.ProductoUpdateDto;
import com.coderhouse.proyectofinal.entity.*;
import com.coderhouse.proyectofinal.exception.DbException;
import com.coderhouse.proyectofinal.exception.UserNotFoundException;
import com.coderhouse.proyectofinal.repository.FacturaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

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
        return facturaRepository.findById(id).orElseThrow(() -> new DbException("No se encontró la factura con id: " + id));
    }

    public Factura createFactura(FacturaDto facturaDto) {
        Cliente cliente = checkCliente(facturaDto);

        Empresa empresa = empresaService.getEmpresaById(facturaDto.getEmpresaId());

        Factura factura = new Factura();

        factura.setFecha(checkTimeApi());
        factura.setTipoFactura('A');
        factura.setCliente(cliente);
        factura.setEmpresa(empresa);


        List<DetalleFactura> detallesFacturas = new ArrayList<>();

        Double total = 0.0;


        for (Producto producto : productoService.checkStockArray(facturaDto.getProductos()).getProductos()) {
            Long id = producto.getId();
            Integer cantidad = facturaDto.getProductos()
                    .stream()
                    .filter(p -> p.getId().equals(id))
                    .findFirst()
                    .map(ProductoUpdateDto::getCantidad)
                    .orElse(0); // EFE

            DetalleFactura detalleFactura = crearDetalle(producto, cantidad);
            detallesFacturas.add(detalleFactura);
            detalleFactura.setFactura(factura);

            total += detalleFactura.getTotalParcial();
        }


        factura.setDetallesFacturas(detallesFacturas);
        factura.setTotalFactura(total);

        facturaRepository.save(factura);

        return factura;
    }




    private LocalDateTime checkTimeApi() {
        Optional<Date> fecha = timeApi.getTime();
        if (fecha.isPresent()) {
            return fecha.get().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
        }else {
            return LocalDateTime.now();
        }

    }


    private DetalleFactura crearDetalle(Producto producto, int cantidad) {
        DetalleFactura detalleFactura = new DetalleFactura();
        DetalleProducto detalleProducto = new DetalleProducto();

        detalleProducto.setNombre(producto.getNombre());
        detalleProducto.setDescripcion(producto.getDescripcion());
        detalleProducto.setPrecioUnitario(producto.getPrecio());

        detalleFactura.setDetalleProducto(detalleProducto);
        detalleFactura.setCantidad(cantidad);

        detalleFactura.setTotalParcial(producto.getPrecio() * cantidad);

        return detalleFactura;

    }

    public Cliente checkCliente(FacturaDto facturaDto) {
        if(facturaDto.getClienteId() != null) {
            return clienteService.getClienteById(facturaDto.getClienteId());
        } else {
            try {
                return clienteService.getClienteByDni(facturaDto.getCliente().getDni());

            } catch (UserNotFoundException e) {
                return clienteService.saveCliente(facturaDto.getCliente());
            }
        }
    }

}
