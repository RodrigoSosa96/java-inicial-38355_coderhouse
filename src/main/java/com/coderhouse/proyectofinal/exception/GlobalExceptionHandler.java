package com.coderhouse.proyectofinal.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DbException.class)
    public ResponseEntity<Object> studentNotFoundResponse(DbException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), ex.getMessage());
        return this.handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> userNotFoundResponse(UserNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), ex.getMessage());
        return this.handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }



    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(err -> {
            String fieldName = ((FieldError) err).getField();
            String errorMessage = err.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), status.toString(), errors);

        return this.handleExceptionInternal(ex, errorResponse, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), ex.getCause().getMessage());
        return this.handleExceptionInternal(ex, errorResponse, headers, status, request);
    }

}
