package com.donato.challenge.repository;

import com.donato.challenge.entities.ApiCallRequestHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ApiCallRequestHistoryRepository extends JpaRepository<ApiCallRequestHistory, Long>, JpaSpecificationExecutor<ApiCallRequestHistory>  {
    ApiCallRequestHistory findFirstByOrderByTimestampDesc();


}
