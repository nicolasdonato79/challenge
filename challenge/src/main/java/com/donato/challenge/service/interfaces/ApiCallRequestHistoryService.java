package com.donato.challenge.service.interfaces;

import com.donato.challenge.entities.ApiCallRequestHistory;
import com.donato.challenge.exception.ApiHistoryIOException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ApiCallRequestHistoryService {

    List<ApiCallRequestHistory> getAll();

    ApiCallRequestHistory saveCall(ApiCallRequestHistory apiCallRequestHistory);

    Page<ApiCallRequestHistory> search(String code, int pageNo, int pageSize,
                                              String sortField, String sortDirection);

    ApiCallRequestHistory findLastSuccessfulResponse() throws ApiHistoryIOException;

}
