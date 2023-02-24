package com.donato.challenge.service.interfaces;

import com.donato.challenge.entities.ApiCallRequestHistory;

import java.util.List;

public interface ApiCallRequestHistoryService {

    public List<ApiCallRequestHistory> getAll();

    public ApiCallRequestHistory findFirstByOrderByTimestampDesc();

    public ApiCallRequestHistory saveCall(ApiCallRequestHistory apiCallRequestHistory);



}
