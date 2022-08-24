package com.coderhouse.proyectofinal.service;

import com.coderhouse.proyectofinal.dto.CheckArrayProductosDto;
import com.coderhouse.proyectofinal.dto.CheckProductoDto;
import com.coderhouse.proyectofinal.dto.ProductoUpdateDto;
import com.coderhouse.proyectofinal.entity.Producto;

import java.util.List;

public interface ProductoService {
    Producto getProductoById(Long id);
    Producto getProductoByNombre(String nombre);
    Iterable<Producto> getAllProductos();
    Iterable<Producto> getFromIds(List<Long> ids);
    Producto saveProducto(Producto producto);
    Producto updateProducto(Long id, Producto producto);
    String deleteProducto(Long id);
    String modifyStock(Long id, Integer cantidad, String operacion);
    Iterable<Producto> modifyStock(List<ProductoUpdateDto> productoUpdates);
    CheckArrayProductosDto checkStockArray(List<ProductoUpdateDto> productosUpdates);
    CheckProductoDto checkStock(Producto producto, Integer cantidad, String operacion);
}
