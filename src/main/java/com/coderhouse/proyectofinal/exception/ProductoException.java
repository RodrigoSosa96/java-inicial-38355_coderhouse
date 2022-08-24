package com.coderhouse.proyectofinal.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class ProductoException extends RuntimeException {
    private final List<String> errorResponse;
    public ProductoException(List<String> errorResponse) {
        this.errorResponse = errorResponse;
    }
    public String getMessage() {
        return String.join(",", errorResponse);
    }
}
