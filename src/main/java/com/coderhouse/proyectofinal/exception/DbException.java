package com.coderhouse.proyectofinal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Employee Not Found") //404
public class DbException extends RuntimeException {
    public DbException(String message) {
        super(message);
    }
}
