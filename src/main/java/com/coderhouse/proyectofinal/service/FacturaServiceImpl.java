package com.coderhouse.proyectofinal.service;

import com.coderhouse.proyectofinal.dto.DetalleFacturaDto;
import com.coderhouse.proyectofinal.dto.DetalleProductoDto;
import com.coderhouse.proyectofinal.dto.ProductoUpdateDto;
import com.coderhouse.proyectofinal.dto.request.RequestFacturaDto;
import com.coderhouse.proyectofinal.entity.*;
import com.coderhouse.proyectofinal.repository.FacturaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacturaServiceImpl implements FacturaService {

    private final FacturaRepository facturaRepository;
    private final ClienteService clienteService;
    private final ProductoService productoService;
    private final EmpresaService empresaService;

    public FacturaServiceImpl(FacturaRepository facturaRepository, ClienteService clienteService, ProductoService productoService, EmpresaService empresaService) {
        this.facturaRepository = facturaRepository;
        this.clienteService = clienteService;
        this.productoService = productoService;
        this.empresaService = empresaService;
    }

    public Factura getFacturaById(Long id) {
        return facturaRepository.findById(id).orElse(null);
    }

    public Factura createFactura(RequestFacturaDto requestFacturaDto) {
        Cliente cliente = clienteService.getClienteById(requestFacturaDto.getClienteId());
        Empresa empresa = empresaService.getEmpresaById(requestFacturaDto.getEmpresaId());

        Iterable<Producto> productos = productoService.getFromIds(requestFacturaDto.getProductosIds());

        Factura factura = new Factura();

        factura.setFecha(requestFacturaDto.getFecha());
        factura.setTipoFactura('A');
        factura.setCliente(cliente);
        factura.setEmpresa(empresa);

        List<ProductoUpdateDto> productosDto = requestFacturaDto.getProductos();
        List<DetalleFactura> detalleFacturas = new ArrayList<>();
        List<DetalleFacturaDto> detalleFacturaDtos = new ArrayList<>();



        Double total = 0.0;
        for(ProductoUpdateDto productoDto: productosDto) {
            Long id = productoDto.getId();
            String operation = productoDto.getOperacion();
            Integer cantidad = productoDto.getCantidad();

            Producto producto = productoService.getProductoById(id);
            productoService.checkStock(producto, cantidad, operation);

            DetalleFactura detalleFactura = new DetalleFactura();
            DetalleFacturaDto detalleFacturaDto = new DetalleFacturaDto();
            DetalleProductoDto detalleProductoDto = new DetalleProductoDto();

            detalleFactura.setNombreProducto(producto.getNombre());
            detalleFactura.setCantidad(cantidad);
            detalleFactura.setPrecioUnitario(producto.getPrecio());
            detalleFactura.setFactura(factura);

            detalleProductoDto.setNombre(producto.getNombre());
            detalleProductoDto.setDescripcion(producto.getDescripcion());
            detalleProductoDto.setPrecioUnitario(producto.getPrecio());

            detalleFacturaDto.setProducto(detalleProductoDto);
            detalleFacturaDto.setCantidad(cantidad);
            detalleFacturaDto.setTotalParcial(producto.getPrecio() * cantidad);

            detalleFacturas.add(detalleFactura);
//            factura.agregarDetalleFactura(detalleFactura);
            detalleFacturaDtos.add(detalleFacturaDto);

            total += detalleFacturaDto.getTotalParcial();
        }

        factura.setDetalleFactura(detalleFacturas);
        factura.setTotalFactura(total);

        facturaRepository.save(factura);

        return factura;
    }

    public DetalleFactura createDetalleFactura(DetalleFacturaDto detalleFacturaDto) {


        return null;
    }

    public Factura saveFactura(Factura factura) {
        return facturaRepository.save(factura);
    }


}
