package com.donato.challenge.repository;

import com.donato.challenge.entities.ApiCallRequestHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ApiCallRequestHistoryRepository extends JpaRepository<ApiCallRequestHistory, Long>, JpaSpecificationExecutor<ApiCallRequestHistory>  {
    ApiCallRequestHistory findFirstByOrderByTimestampDesc();
    @Query(nativeQuery = true, value="SELECT id, endpoint, method, request_body, response_body, \"timestamp\", status FROM public.api_call_request_history ch where ch.status=200 and  ch.endpoint = '/api-rest' and (request_body != NULL OR request_body!='') order by id desc limit 1")
    ApiCallRequestHistory findLastValidResponse();

}
