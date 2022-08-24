package com.coderhouse.proyectofinal.service;

import com.coderhouse.proyectofinal.dto.CheckArrayProductosDto;
import com.coderhouse.proyectofinal.dto.CheckProductoDto;
import com.coderhouse.proyectofinal.dto.ProductoUpdateDto;
import com.coderhouse.proyectofinal.dto.ResponseDto;
import com.coderhouse.proyectofinal.entity.Producto;
import com.coderhouse.proyectofinal.exception.DbException;
import com.coderhouse.proyectofinal.exception.ProductoException;
import com.coderhouse.proyectofinal.repository.ProductoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
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

    public Iterable<Producto> saveProducto(Iterable<Producto> productos) {
        return productoRepository.saveAll(productos);
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

    public ResponseDto deleteProducto(Long id) {
        Producto producto = getProductoById(id);
        productoRepository.deleteById(id);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Producto eliminado");
        responseDto.putFieldErrors("producto", producto.getNombre());
        return responseDto;
    }
    public Producto modifyStock(Long id, Integer cantidad, String operacion ) {
        Producto producto = getProductoById(id);
        CheckProductoDto productoChecked = checkStock(producto, cantidad, operacion);
        if(productoChecked.getStatus().equals("OK")) {
            producto.setStock(productoChecked.getStock());
            productoRepository.save(producto);
            return producto;
//            return "Stock modificado: " + producto.getNombre() + " - " + producto.getStock();
        } else {
            throw new ProductoException(productoChecked.getErrorResponse(), null);
        }
    }

    public Iterable<Producto> modifyStock(List<ProductoUpdateDto> productoUpdates) {
        CheckArrayProductosDto prod = checkStockArray(productoUpdates);
        return productoRepository.saveAll(prod.getProductos());
    }



    public CheckArrayProductosDto checkStockArray(List<ProductoUpdateDto> productosUpdates) {
        CheckArrayProductosDto checkArrayProductos = new CheckArrayProductosDto();

        List<Long> ids = productosUpdates.stream().map(ProductoUpdateDto::getId).collect(Collectors.toList());

        Iterable<Producto> productos = getFromIds(ids);

        List<String> errorResponseArr = new ArrayList<>();

        checkArrayProductos.setStatus("OK");
        for (Producto producto : productos) {
            for (ProductoUpdateDto productoUpdateDto : productosUpdates) {
                if (Objects.equals(producto.getId(), productoUpdateDto.getId())) {
                    CheckProductoDto checkedProducto = checkStock(producto, productoUpdateDto.getCantidad(), productoUpdateDto.getOperacion());

                    if(checkedProducto.getStatus().equals("OK")) {

                        producto.setStock(checkedProducto.getStock());

                    } else {
                        checkArrayProductos.setStatus("ERROR");
                        errorResponseArr.add(checkedProducto.getErrorResponse());
                    }
                }
            }
        }
        if(checkArrayProductos.getStatus().equals("ERROR")) {
            throw new ProductoException("Error modificando el stock", errorResponseArr);
        }else {
            checkArrayProductos.setProductos(productos);
            return checkArrayProductos;
        }
    }

    public CheckProductoDto checkStock(Producto producto, Integer cantidad, String operacion) {
        CheckProductoDto checkProductoDto = new CheckProductoDto();
        int stockActual = producto.getStock();
        switch (operacion.toUpperCase()) {
            case "SUMAR":
                producto.setStock(stockActual + cantidad);
                checkProductoDto.setStatus("OK");

                break;
            case "RESTAR":
                if (stockActual >= cantidad) {
                    producto.setStock(stockActual - cantidad);
                    checkProductoDto.setStatus("OK");
                } else {
                    checkProductoDto.setStatus("ERROR");
                    checkProductoDto.setErrorResponse("No se pudo restar stock, quedan " + stockActual + " unidades en stock del producto " + producto.getNombre());
                }
                break;
            default:
                checkProductoDto.setErrorResponse("Operacion no valida");
                checkProductoDto.setStatus("ERROR");
        }
        if (checkProductoDto.getStatus().equals("OK")) {
            checkProductoDto.setStock(producto.getStock());
        }
//        checkProductoDto.setProducto(producto);
        return checkProductoDto;
    }


}
