package com.example.cinemaimpl.exception.handler;

import com.example.cinemaimpl.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@AllArgsConstructor
@RestControllerAdvice
public class CinemaExceptionHandler extends ResponseEntityExceptionHandler {
    private ErrorAttributes errorAttributes;

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleNotFoundException(NotFoundException exception, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(getErrorAttributes(request));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    private Map<String, Object> getErrorAttributes(WebRequest request) {
        return errorAttributes.getErrorAttributes(request, ErrorAttributeOptions.defaults());
    }
}
