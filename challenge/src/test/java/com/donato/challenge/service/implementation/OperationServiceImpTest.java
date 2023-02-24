package com.donato.challenge.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class OperationServiceImpTest {

    @Mock
    private ExternalServiceImp externalServiceImp;

    @InjectMocks
    private OperationServiceImp operationServiceImp;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void add() {

        try {
            when(operationServiceImp.add(5.0, 5.0)).thenReturn(11.0);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        // when(operationServiceImp.add(1.0, 1.0)).thenThrow(new Throwable("Error en el servicio externo"));
    }
}