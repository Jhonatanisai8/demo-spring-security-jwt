package com.example.demo.app.controllers.exceptionsHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlerApp {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
        String message = "Método HTTP no soportado: " + ex.getMethod();
        String[] supportedMethods = ex.getSupportedMethods();
        if (supportedMethods != null) {
            message += ". Métodos soportados: " + String.join(", ", supportedMethods);
        }
        return new ResponseEntity<>(message, HttpStatus.METHOD_NOT_ALLOWED);
    }
}
