package com.donato.challenge.controller;

import com.donato.challenge.entities.ApiCallRequestHistory;
import com.donato.challenge.service.interfaces.ApiCallRequestHistoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.*;

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
    void buscar() {
        List<ApiCallRequestHistory> lista= new ArrayList<>();
        lista.add(call);
         int pageNumber = 0;
         int pageSize = 3;
         PageRequest pageable = PageRequest.of(pageNumber, pageSize);
         Page<ApiCallRequestHistory> page= new PageImpl<>(lista, pageable, lista.size());


         when(apiCallRequestHistoryService.search("/api-rest", 1, 10, "endpoint", "desc")).thenReturn(page);

         Optional<Integer> pag= Optional.of(1);
         Optional<Integer> size=Optional.of(10);
         Page<ApiCallRequestHistory> page2= apiRestCallHistoryController.find("/api-rest", pag, size,"endpoint", "desc" );
         Assertions.assertNotNull(page2);
         assertEquals(page.getTotalElements(), page2.getTotalElements());
         assertEquals(page.getContent().size(), page2.getContent().size());
    }


}