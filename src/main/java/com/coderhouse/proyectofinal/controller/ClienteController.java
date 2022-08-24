package com.coderhouse.proyectofinal.controller;

import com.coderhouse.proyectofinal.dto.ClienteDto;
import com.coderhouse.proyectofinal.dto.ResponseDto;
import com.coderhouse.proyectofinal.entity.Cliente;
import com.coderhouse.proyectofinal.exception.ErrorResponse;
import com.coderhouse.proyectofinal.service.ClienteService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping()
    public Iterable<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @GetMapping("/{id}")
    public Cliente getCliente(@PathVariable Long id) {
        return clienteService.getClienteById(id);
    }

    @PostMapping()
    public Cliente saveCliente(@Valid @RequestBody Cliente cliente) {
        return clienteService.saveCliente(cliente);
    }

    @PutMapping("/{id}")
    public Cliente updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        return clienteService.updateCliente(id, cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseDto deleteCliente(@PathVariable Long id) {
        return clienteService.deleteClienteById(id);
    }

    @GetMapping("/{id}/edad")
    public ClienteDto getEdadCliente(@PathVariable Long id) {
        return clienteService.getEdadCliente(id);

    }

    @GetMapping("/")
    public Cliente getClienteByDni(@RequestParam String dni) {
        return clienteService.getClienteByDni(dni);
    }



    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponse duplicatedDni() {
        return new ErrorResponse(LocalDateTime.now(), "Dni duplicado");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DateTimeParseException.class)
    public ErrorResponse badDateFormat() {
        return new ErrorResponse(LocalDateTime.now(), "Formato de fecha incorrecto");
    }
}
