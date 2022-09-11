package com.example.cinemaimpl.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.FieldError;

@Getter
@Setter
@AllArgsConstructor
public class ValidationExceptionResponse {
    private String fieldName;
    private String message;

    public ValidationExceptionResponse(FieldError fieldError) {
        this.fieldName = fieldError.getField();
        this.message = fieldError.getDefaultMessage();
    }
}
