package com.donato.challenge.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-rest-call-history")
public class ApiRestCallHistoryController {

    @GetMapping
    public ResponseEntity<Double> getHistorial() {
        // TODO: Falta implementar

        double resultado=0.0;
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }



}
