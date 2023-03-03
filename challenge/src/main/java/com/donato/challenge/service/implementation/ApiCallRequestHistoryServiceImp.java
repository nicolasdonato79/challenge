package com.donato.challenge.service.implementation;

import com.donato.challenge.criteria.SearchCriteria;
import com.donato.challenge.criteria.SearchOperation;
import com.donato.challenge.criteria.SearchSpecifications;
import com.donato.challenge.entities.ApiCallRequestHistory;
import com.donato.challenge.repository.ApiCallRequestHistoryRepository;
import com.donato.challenge.service.interfaces.ApiCallRequestHistoryService;
import com.donato.challenge.utils.PageableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class ApiCallRequestHistoryServiceImp implements ApiCallRequestHistoryService {
    @Autowired
    ApiCallRequestHistoryRepository apiCallRequestHistoryRepository;

    private final String ENDPOINT= "endpoint";

    public ApiCallRequestHistory saveCall(ApiCallRequestHistory call){
        return apiCallRequestHistoryRepository.save(call);
    }

    public Page<ApiCallRequestHistory> search(String code, int pageNo, int pageSize,
                                String sortField, String sortDirection) {
        SearchSpecifications<ApiCallRequestHistory> searchSpecifications = new SearchSpecifications<>();

        if (code!=null && !code.isEmpty()) {
            searchSpecifications.add(new SearchCriteria(ENDPOINT, code, SearchOperation.EQUAL));
        }

        return apiCallRequestHistoryRepository.findAll(searchSpecifications,
                PageableUtils.buildPageable(pageNo, pageSize, sortField, sortDirection));
    }

    @Override
    public ApiCallRequestHistory findLastSuccessfulResponse() {
        return apiCallRequestHistoryRepository.findLastValidResponse();
    }


}
