package com.donato.challenge.service.interfaces;

import com.donato.challenge.entities.ApiCallRequestHistory;
import com.donato.challenge.entities.Resp;
import com.donato.challenge.entities.RespWrapper;
import com.donato.challenge.service.implementation.ApiCallRequestHistoryServiceImp;
import com.donato.challenge.service.implementation.ExternalServiceImp;
import com.donato.challenge.service.implementation.OperationServiceImp;
import com.donato.challenge.service.interfaces.ExternalService;
import com.donato.challenge.service.interfaces.OperationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class OperationServiceTest {

    @Mock
    ExternalServiceImp externalService;

    @Mock
    ApiCallRequestHistoryService apiCallRequestHistoryService;

    @InjectMocks
    OperationService operationService = new OperationServiceImp();



    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void add() throws Exception {


        Double x=5.0;
        Double y =5.0;
        ApiCallRequestHistory call = new ApiCallRequestHistory(Long.getLong("1"),200, "POST", "api-test", "body", "resp", new Date(1l));
        when(externalService.getPorcentual(x, y)).thenReturn(Double.valueOf("11"));
        when(apiCallRequestHistoryService.findLastSuccessfulResponse()).thenReturn(call);
        RespWrapper expected=  new RespWrapper(new Resp(Double.valueOf(11)), HttpStatus.OK);

        RespWrapper resp=  operationService.add(x, y);
        Assertions.assertNotNull(resp);
        Assertions.assertEquals(expected, resp);

    }
}