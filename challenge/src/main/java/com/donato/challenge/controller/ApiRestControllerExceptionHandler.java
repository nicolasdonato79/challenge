package com.donato.challenge.controller;

import com.donato.challenge.exception.ServerExternalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiRestControllerExceptionHandler {

    @ExceptionHandler(ServerExternalException.class)
    public ResponseEntity<String> handleException(ServerExternalException ex) {
        String mensaje = "Ocurrió una excepción al llamar un servicio Externo: " + ex.getMessage();
        return new ResponseEntity<>(mensaje, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        String mensaje = "Ocurrió un error interno en el servidor: " + ex.getMessage();
        return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

