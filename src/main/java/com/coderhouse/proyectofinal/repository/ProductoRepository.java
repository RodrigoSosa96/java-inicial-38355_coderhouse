package com.coderhouse.proyectofinal.repository;

import com.coderhouse.proyectofinal.entity.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoRepository extends CrudRepository<Producto, Long> {
    Producto getProductoByNombre(String nombre);
}
