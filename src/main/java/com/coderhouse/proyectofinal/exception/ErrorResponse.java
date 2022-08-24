package com.coderhouse.proyectofinal.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private LocalDateTime timestamp;
    private String message;
    private Map<String, String> fieldErrors;
    private List<String> arrayErrors;

    public ErrorResponse(LocalDateTime timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }
    public ErrorResponse(LocalDateTime timestamp, String message, Map<String, String> fieldErrors) {
        this.timestamp = timestamp;
        this.message = message;
        this.fieldErrors = fieldErrors;
    }
    public ErrorResponse(LocalDateTime timestamp, String message,Map<String, String> fieldErrors, List<String> arrayErrors) {
        this.timestamp = timestamp;
        this.message = message;
        this.fieldErrors = fieldErrors;
        this.arrayErrors = arrayErrors;
    }
}
