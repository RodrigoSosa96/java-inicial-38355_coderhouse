package com.coderhouse.proyectofinal.repository;

import com.coderhouse.proyectofinal.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
    Optional<Cliente> findByDni(String dni);

}
