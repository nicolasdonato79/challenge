package com.donato.challenge.service.implementation;

import com.donato.challenge.entities.ApiCallRequestHistory;
import com.donato.challenge.repository.ApiCallRequestHistoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ApiCallRequestHistoryServiceImpTest {

    @Mock
    ApiCallRequestHistoryRepository apiCallRequestHistoryRepository;

    @InjectMocks
    ApiCallRequestHistoryServiceImp apiCallRequestHistoryService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveCall() {
        ApiCallRequestHistory call= new ApiCallRequestHistory(200,"POST","api-test", "body","resp",new Date());


        ApiCallRequestHistory savedCall=apiCallRequestHistoryService.saveCall(call);
        Assertions.assertEquals(call.getStatus(), savedCall.getStatus());
        Assertions.assertEquals(call.getRequestBody(), savedCall.getRequestBody());
        Assertions.assertEquals(call.getEndpoint(), savedCall.getEndpoint());
        Assertions.assertEquals(call.getResponseBody(), savedCall.getResponseBody());
        Assertions.assertEquals(call.getMethod(), savedCall.getMethod());
        Assertions.assertNotNull(savedCall.getTimestamp());

    }
}