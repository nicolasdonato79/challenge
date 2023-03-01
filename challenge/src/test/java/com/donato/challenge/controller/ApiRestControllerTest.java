package com.donato.challenge.controller;

import com.donato.challenge.entities.Resp;
import com.donato.challenge.entities.RespWrapper;
import com.donato.challenge.exception.ApiHistoryIOException;
import com.donato.challenge.repository.ApiCallRequestHistoryRepository;
import com.donato.challenge.service.implementation.ApiCallRequestHistoryServiceImp;
import com.donato.challenge.service.interfaces.ApiCallRequestHistoryService;
import com.donato.challenge.service.interfaces.OperationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ApiRestControllerTest {
    @Mock
    OperationService operationService;

    @InjectMocks
    ApiRestController apiRestController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void add() throws ApiHistoryIOException, JsonProcessingException {
        RespWrapper resp = new RespWrapper(new Resp(Double.valueOf(99)), HttpStatus.OK);

        double x=1.0;
        double y=1.0;
        when(operationService.add(x, y)).thenReturn(resp);
        RespWrapper resp2= operationService.add(x, y);
        assertEquals(resp.getResp(), resp2.getResp());
        assertEquals(resp.getStatus(), resp2.getStatus());



    }
}