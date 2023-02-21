package com.donato.challenge.service.implementation;

import com.donato.challenge.entities.ApiCallRequestHistory;
import com.donato.challenge.repository.ApiCallRequestHistoryRepository;
import com.donato.challenge.service.interfaces.ApiCallRequestHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public ApiCallRequestHistory save(ApiCallRequestHistory apiCallRequestHistory) {
        return    apiCallRequestHistoryRepository.save(apiCallRequestHistory);
    }

    @Async
    void persistirHistorial(ApiCallRequestHistory calculo){
        apiCallRequestHistoryRepository.save(calculo);
    }
}
