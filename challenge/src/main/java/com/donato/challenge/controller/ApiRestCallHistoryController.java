package com.donato.challenge.controller;

import com.donato.challenge.entities.ApiCallRequestHistory;
import com.donato.challenge.service.interfaces.ApiCallRequestHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api-rest-call-history")
public class ApiRestCallHistoryController {

    @Autowired
    ApiCallRequestHistoryService apiCallRequestHistoryService;

    @PostMapping(value = "/find")
    public Page<ApiCallRequestHistory> find(@RequestParam("code") String code, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
                                            @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        return apiCallRequestHistoryService.search(code, currentPage, pageSize, sortField, sortDir);

    }


}