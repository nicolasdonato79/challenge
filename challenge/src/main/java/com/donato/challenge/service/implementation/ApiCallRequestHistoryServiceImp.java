package com.donato.challenge.service.implementation;

import com.donato.challenge.entities.ApiCallRequestHistory;
import com.donato.challenge.repository.ApiCallRequestHistoryRepository;
import com.donato.challenge.searchCriteria.SearchCriteria;
import com.donato.challenge.searchCriteria.SearchOperation;
import com.donato.challenge.searchCriteria.SearchSpecifications;
import com.donato.challenge.service.interfaces.ApiCallRequestHistoryService;
import com.donato.challenge.utils.PageableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Service
public class ApiCallRequestHistoryServiceImp implements ApiCallRequestHistoryService {
    @Autowired
    ApiCallRequestHistoryRepository apiCallRequestHistoryRepository;

    @Override
    public List<ApiCallRequestHistory> getAll() {
        return apiCallRequestHistoryRepository.findAll();
    }

    @Override
    public ApiCallRequestHistory findFirstByOrderByTimestampDesc() {
       return apiCallRequestHistoryRepository.findFirstByOrderByTimestampDesc();
    }

    @Async
    public ApiCallRequestHistory saveCall(ApiCallRequestHistory call){
        return apiCallRequestHistoryRepository.save(call);
    }

    public Page<ApiCallRequestHistory> search(String code, int pageNo, int pageSize,
                                String sortField, String sortDirection) {
        SearchSpecifications<ApiCallRequestHistory> searchSpecifications = new SearchSpecifications<>();

        if (code!=null && !code.isEmpty()) {
            searchSpecifications.add(new SearchCriteria("endpoint", code, SearchOperation.EQUAL));
        }

        return apiCallRequestHistoryRepository.findAll(searchSpecifications,
                PageableUtils.buildPageable(pageNo, pageSize, sortField, sortDirection));
    }

}
