package com.donato.challenge.service.interfaces;

import com.donato.challenge.entities.ApiCallRequestHistory;
import com.donato.challenge.exception.ApiHistoryIOException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ApiCallRequestHistoryService {

    public List<ApiCallRequestHistory> getAll();

    public ApiCallRequestHistory findFirstByOrderByTimestampDesc();

    public ApiCallRequestHistory saveCall(ApiCallRequestHistory apiCallRequestHistory);

    public Page<ApiCallRequestHistory> search(String code, int pageNo, int pageSize,
                                              String sortField, String sortDirection);

    public ApiCallRequestHistory findLastSuccessfulResponse() throws ApiHistoryIOException;

}
