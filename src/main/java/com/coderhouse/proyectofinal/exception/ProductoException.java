package com.coderhouse.proyectofinal.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductoException extends RuntimeException {
    private final List<String> errorResponse;

    public ProductoException(String message, List<String> errorResponse) {
        super(message);
        this.errorResponse = errorResponse;
    }

}
