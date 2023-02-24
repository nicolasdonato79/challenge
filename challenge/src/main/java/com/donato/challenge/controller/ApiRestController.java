package com.donato.challenge.controller;

import com.donato.challenge.entities.OperationRequest;
import com.donato.challenge.entities.Respuesta;
import com.donato.challenge.service.interfaces.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-rest")
public class ApiRestController {

    @Autowired
    private OperationService operationService;
//
    @PostMapping
    public ResponseEntity<Respuesta> add(@RequestBody OperationRequest request) {
//        double result = operationService.add(request.getX(), request.getY());
        return new ResponseEntity<>(new Respuesta(operationService.add(request.getX(), request.getY())), HttpStatus.OK);
    }

//    @PostMapping()
//    public ResponseEntity<Respuesta> sum(@RequestParam double x, @RequestParam double y) {
//        return new ResponseEntity<Respuesta>(new Respuesta(operationService.sumar(x, y)), HttpStatus.OK);
//    }
//    @GetMapping()
//    public ResponseEntity<Respuesta> sum() {
//        Respuesta res=new Respuesta(operationService.add(3, 4));
//        return new ResponseEntity<>(res, HttpStatus.OK);
//    }

}