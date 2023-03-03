package com.donato.challenge.service.interfaces;

import com.donato.challenge.criteria.SearchCriteria;
import com.donato.challenge.criteria.SearchOperation;
import com.donato.challenge.criteria.SearchSpecifications;
import com.donato.challenge.entities.ApiCallRequestHistory;
import com.donato.challenge.exception.ApiHistoryIOException;
import com.donato.challenge.repository.ApiCallRequestHistoryRepository;
import com.donato.challenge.service.implementation.ApiCallRequestHistoryServiceImp;
import com.donato.challenge.utils.PageableUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

import java.util.*;
import java.util.function.Function;

import static org.mockito.Mockito.when;

class ApiCallRequestHistoryServiceTest {

    @Mock
    ApiCallRequestHistoryRepository apiCallRequestHistoryRepository;

    @InjectMocks
    ApiCallRequestHistoryService apiCallRequestHistoryService = new ApiCallRequestHistoryServiceImp();


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    ApiCallRequestHistory call = new ApiCallRequestHistory(Long.getLong("1"),200, "POST", "api-test", "body", "resp", new Date(1l));

    @Test
    void saveCall() {

        when(apiCallRequestHistoryRepository.save(call)).thenReturn(call);

        ApiCallRequestHistory savedCall = apiCallRequestHistoryService.saveCall(call);
        Assertions.assertNotNull(savedCall);
        Assertions.assertEquals(call,savedCall);
    }

//    @Test
//    void getAll() {
//        when(apiCallRequestHistoryRepository.findAll()).thenReturn(Collections.singletonList(call));
//        Assertions.assertNotNull(apiCallRequestHistoryRepository.findAll());
//        Assert.notEmpty(apiCallRequestHistoryRepository.findAll());
//    }

    @Test
    void findLastSuccessfulResponse() throws ApiHistoryIOException {
        when(apiCallRequestHistoryRepository.findLastValidResponse()).thenReturn(call);
        ApiCallRequestHistory resp=  apiCallRequestHistoryService.findLastSuccessfulResponse();
        Assertions.assertNotNull(resp);
        Assertions.assertEquals(call, resp);
    }

}