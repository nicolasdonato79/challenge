package com.donato.challenge.controller;

import com.donato.challenge.entities.OperationRequest;
import com.donato.challenge.entities.Respuesta;
import com.donato.challenge.exception.ServerExternalException;
import com.donato.challenge.service.interfaces.OperationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-rest")
public class ApiRestController {

    @Autowired
    private OperationService operationService;

//    Este metodo tiene que devolver 404 si falla,
    //tmabien se puede usar el 503 para indicar el 503 Service Unavailable
//    429 si se pasa de intentos dentro del plazo dado
    @PostMapping
    @ExceptionHandler({ServerExternalException.class, JsonProcessingException.class})
    public ResponseEntity<Respuesta> add(@RequestBody OperationRequest request) throws JsonProcessingException {

        return new ResponseEntity<>(new Respuesta(operationService.add(request.getX(), request.getY())), HttpStatus.OK);

    }



}