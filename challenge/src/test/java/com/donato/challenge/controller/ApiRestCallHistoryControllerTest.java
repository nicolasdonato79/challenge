package com.donato.challenge.controller;

import com.donato.challenge.entities.ApiCallRequestHistory;
import com.donato.challenge.service.implementation.ApiCallRequestHistoryServiceImp;
import com.donato.challenge.service.interfaces.ApiCallRequestHistoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class ApiRestCallHistoryControllerTest {

    @Mock
    ApiCallRequestHistoryService apiCallRequestHistoryService;

    @InjectMocks
    ApiRestCallHistoryController apiRestCallHistoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    ApiCallRequestHistory call = new ApiCallRequestHistory(200, "POST", "api-test", "body", "resp", new Date());
    @Test
    void getAll() {
        when(apiCallRequestHistoryService.getAll()).thenReturn(Arrays.asList(call));
        List<ApiCallRequestHistory> list= apiRestCallHistoryController.getAll();
        Assertions.assertNotNull(list);
        Assert.notEmpty(list);
    }

    @Test
    void getPaginated() {
    }

    @Test
    void buscar() {
    }
}