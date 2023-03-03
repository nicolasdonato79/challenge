package com.donato.challenge.controller;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalControllerAdvice {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<String> handleException(HttpServletRequest request) {
        String mensaje= "Error, no existe ese endpoint";
        return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);

    }
}
