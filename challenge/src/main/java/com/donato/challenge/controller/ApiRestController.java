package com.donato.challenge.controller;

import com.donato.challenge.entities.OperationRequest;
import com.donato.challenge.entities.Resp;
import com.donato.challenge.exception.ApiHistoryIOException;
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


    @PostMapping
    @ExceptionHandler({ServerExternalException.class, JsonProcessingException.class, ApiHistoryIOException.class})
    public ResponseEntity<Resp> add(@RequestBody OperationRequest request) throws JsonProcessingException, ApiHistoryIOException {

        return new ResponseEntity<>(new Resp(operationService.add(request.getX(), request.getY())), HttpStatus.OK);

    }



}