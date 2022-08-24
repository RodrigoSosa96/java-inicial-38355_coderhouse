package com.coderhouse.proyectofinal.service;

import com.coderhouse.proyectofinal.dto.ClienteDto;
import com.coderhouse.proyectofinal.dto.ResponseDto;
import com.coderhouse.proyectofinal.entity.Cliente;

public interface ClienteService {

    Iterable<Cliente> getAllClientes();
    Cliente getClienteById(Long id);
    Cliente getClienteByDni(String dni);
    Cliente saveCliente(Cliente cliente);
    Cliente updateCliente(Long id, Cliente cliente);
    ResponseDto deleteClienteById(Long id);

    ClienteDto getEdadCliente(Long id);
}
