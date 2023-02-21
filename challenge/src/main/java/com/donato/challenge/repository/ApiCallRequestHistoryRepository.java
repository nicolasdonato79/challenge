package com.donato.challenge.repository;

import com.donato.challenge.entities.ApiCallRequestHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiCallRequestHistoryRepository extends JpaRepository<ApiCallRequestHistory, Long> {
    ApiCallRequestHistory findFirstByOrderByTimestampDesc();
}
