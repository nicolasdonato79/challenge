package com.donato.challenge.service.interfaces;

import com.donato.challenge.entities.ApiCallRequestHistory;
import com.donato.challenge.repository.ApiCallRequestHistoryRepository;
import com.donato.challenge.service.implementation.ApiCallRequestHistoryServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.mockito.Mockito.when;

class ApiCallRequestHistoryServiceTest {

    @Mock
    ApiCallRequestHistoryRepository apiCallRequestHistoryRepository;

    @InjectMocks
    ApiCallRequestHistoryServiceImp apiCallRequestHistoryService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    ApiCallRequestHistory call = new ApiCallRequestHistory(200, "POST", "api-test", "body", "resp", new Date());

    @Test
    void saveCall() {

        when(apiCallRequestHistoryRepository.save(call)).thenReturn(call);

        ApiCallRequestHistory savedCall = apiCallRequestHistoryService.saveCall(call);
        Assertions.assertEquals(call.getStatus(), savedCall.getStatus());
        Assertions.assertEquals(call.getRequestBody(), savedCall.getRequestBody());
        Assertions.assertEquals(call.getEndpoint(), savedCall.getEndpoint());
        Assertions.assertEquals(call.getResponseBody(), savedCall.getResponseBody());
        Assertions.assertEquals(call.getMethod(), savedCall.getMethod());
        Assertions.assertNotNull(savedCall.getTimestamp());


    }

    @Test
    void getAll() {
        when(apiCallRequestHistoryRepository.findAll()).thenReturn(Collections.singletonList(call));
        Assertions.assertNotNull(apiCallRequestHistoryRepository.findAll());
        Assert.notEmpty(apiCallRequestHistoryRepository.findAll());
    }

    @Test
    void findLastSuccessfulResponse() {
        when(apiCallRequestHistoryRepository.findLastValidResponse()).thenReturn(call);
        ApiCallRequestHistory resp=  apiCallRequestHistoryService.findLastSuccessfulResponse();
        Assertions.assertEquals(call.getStatus(), resp.getStatus());
        Assertions.assertEquals(call.getRequestBody(), resp.getRequestBody());
        Assertions.assertEquals(call.getEndpoint(), resp.getEndpoint());
        Assertions.assertEquals(call.getResponseBody(), resp.getResponseBody());
        Assertions.assertEquals(call.getMethod(), resp.getMethod());
    }
}