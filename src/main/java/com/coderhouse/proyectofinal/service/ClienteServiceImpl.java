package com.coderhouse.proyectofinal.service;

import com.coderhouse.proyectofinal.dto.ClienteDto;
import com.coderhouse.proyectofinal.entity.Cliente;
import com.coderhouse.proyectofinal.exception.UserNotFoundException;
import com.coderhouse.proyectofinal.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Iterable<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id).orElseThrow(
                () -> UserNotFoundException.createWith(id)
        );
    }

    public Cliente getClienteByDni(String dni) {
        return clienteRepository.findByDni(dni).orElseThrow(
                () -> UserNotFoundException.createWith(dni)
        );
    }

    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(Long id, Cliente cliente) {
        Cliente clienteActual = getClienteById(id);
        if (cliente.getNombre() != null) {
            clienteActual.setNombre(cliente.getNombre());
        }
        if (cliente.getDni() != null) {
            clienteActual.setDni(cliente.getDni());
        }
        if (cliente.getDireccion() != null) {
            clienteActual.setDireccion(cliente.getDireccion());
        }
        if (cliente.getFechaNacimiento() != null) {
            clienteActual.setFechaNacimiento(cliente.getFechaNacimiento());
        }
        return clienteRepository.save(clienteActual);
    }

    public String deleteClienteById(Long id) {
        Cliente cliente = getClienteById(id);
        clienteRepository.delete(cliente);
        return "Cliente con dni '"+ cliente.getDni() +"' eliminado";
    }

    public ClienteDto getEdadCliente(Long id) {
        Cliente cliente = getClienteById(id);
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setNombre(cliente.getNombre());
        clienteDto.setApellido(cliente.getApellido());
        clienteDto.setDireccion(cliente.getDireccion());
        clienteDto.setDni(cliente.getDni());

        LocalDate fechaNacimiento = cliente.getFechaNacimiento();
        int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
        clienteDto.setEdad(edad);
        return clienteDto;
    }
}
