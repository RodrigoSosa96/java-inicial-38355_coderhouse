package com.coderhouse.proyectofinal.service;

import com.coderhouse.proyectofinal.dto.ProductoUpdateDto;
import com.coderhouse.proyectofinal.entity.Producto;
import com.coderhouse.proyectofinal.exception.DbException;
import com.coderhouse.proyectofinal.exception.ProductoException;
import com.coderhouse.proyectofinal.repository.ProductoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductoServiceImpl implements ProductoService {
    final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto getProductoById(Long id) {
        return productoRepository.findById(id).orElseThrow(() -> new DbException("Producto no encontrado"));
    }
    public Producto getProductoByNombre(String nombre) {
        return productoRepository.getProductoByNombre(nombre);
    }

    public Iterable<Producto> getAllProductos() {
        return productoRepository.findAll();
    }
    public Iterable<Producto> getFromIds(List<Long> ids) {
        return productoRepository.findAllById(ids);
    }

    public Producto saveProducto(Producto producto) {
//        if(producto.getStock() == null) producto.setStock(0);
        return productoRepository.save(producto);
    }

    public Producto updateProducto(Long id, Producto producto) {
        Producto productoActual = getProductoById(id);
        if (productoActual == null) {
            return null;
        }
        if (producto.getNombre() != null) {
            productoActual.setNombre(producto.getNombre());
        }
        if (producto.getPrecio() != null) {
            productoActual.setPrecio(producto.getPrecio());
        }
        if (producto.getStock() != null) {
            productoActual.setStock(producto.getStock());
        }

        return productoRepository.save(productoActual);
    }

    public String deleteProducto(Long id) {
        Producto producto = getProductoById(id);
        productoRepository.deleteById(id);
        return "Producto eliminado: " + producto.getNombre();
    }
    public String modifyStock(Long id, Integer cantidad, String operacion ) {
        Producto producto = checkStock(getProductoById(id), cantidad, operacion);
        productoRepository.save(producto);
        return "Stock modificado: " + producto.getNombre() + " - " + producto.getStock();
    }

    public Iterable<Producto> modifyStock(List<ProductoUpdateDto> productoUpdates) {
        Iterable<Producto> prod = checkStockArray(productoUpdates);
        productoRepository.saveAll(prod);
        return prod;
    }



    public Iterable<Producto> checkStockArray(List<ProductoUpdateDto> productosUpdates) {
        List<Long> ids = productosUpdates.stream().map(ProductoUpdateDto::getId).collect(Collectors.toList());
        Iterable<Producto> productos = getFromIds(ids);
        Set<Producto> productosSet = new HashSet<>();

        for (Producto producto : productos) {
            for (ProductoUpdateDto productoUpdateDto : productosUpdates) {
                if (Objects.equals(producto.getId(), productoUpdateDto.getId())) {
                    productosSet.add(checkStock(producto, productoUpdateDto.getCantidad(), productoUpdateDto.getOperacion()));
                }
            }
        }

        return productosSet;
    }

    public Producto checkStock(Producto producto, Integer cantidad, String operacion) {
        int stockActual = producto.getStock();
        switch (operacion) {
            case "sumar":
                producto.setStock(stockActual + cantidad);
                break;
            case "restar":
                if (stockActual >= cantidad) {
                    producto.setStock(stockActual - cantidad);
                    break;
                } else {
                    throw new ProductoException("No se pudo restar stock, quedan " + stockActual + " unidades en stock");
                }
            default:
                throw new ProductoException("Operacion no valida");
        }
        return producto;
    }


}
