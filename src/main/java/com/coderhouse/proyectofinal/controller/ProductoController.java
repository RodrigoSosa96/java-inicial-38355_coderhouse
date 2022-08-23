package com.coderhouse.proyectofinal.controller;

import com.coderhouse.proyectofinal.dto.ProductoUpdateDto;
import com.coderhouse.proyectofinal.entity.Producto;
import com.coderhouse.proyectofinal.exception.ErrorResponse;
import com.coderhouse.proyectofinal.exception.ProductoException;
import com.coderhouse.proyectofinal.service.ProductoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/productos")
@Slf4j
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/{id}")
    public Producto getProducto(@PathVariable(value = "id") Long id) {
        return productoService.getProductoById(id);
    }

    @GetMapping
    public Iterable<Producto> getProductos() {
        return productoService.getAllProductos();
    }

    @PostMapping
    public Producto saveProducto(@Valid @RequestBody Producto producto) {
        return productoService.saveProducto(producto);
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable(value = "id") Long id, @RequestBody Producto producto) {
        return productoService.updateProducto(id, producto);
    }

    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable(value = "id") Long id) {
        return productoService.deleteProducto(id);
    }

    @PutMapping("/stock/{id}")
    public String restarStock(@PathVariable(value = "id") Long id, @RequestParam(value = "operacion") String operacion, @RequestParam(value ="valor") Integer valor) {
        return productoService.modifyStock(id, valor, operacion);
    }
    @PutMapping("/stock")
    public Iterable<Producto> testStock(@RequestBody List<ProductoUpdateDto> productosUpdates) {
        return productoService.checkStockArray(productosUpdates);
    }


    @ExceptionHandler(ProductoException.class)
    public ErrorResponse handleProductoException(ProductoException ex) {
        log.info("ProductoException: {}", ex.getCause().getMessage());
        return new ErrorResponse(LocalDateTime.now(), ex.getMessage());
    }

}
