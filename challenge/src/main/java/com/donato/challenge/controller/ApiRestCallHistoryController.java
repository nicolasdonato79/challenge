package com.donato.challenge.controller;

import com.donato.challenge.entities.ApiCallRequestHistory;
import com.donato.challenge.entities.OperationRequest;
import com.donato.challenge.service.interfaces.ApiCallRequestHistoryService;
import com.donato.challenge.service.interfaces.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api-rest-call-history")
public class ApiRestCallHistoryController {

    @Autowired
    ApiCallRequestHistoryService apiCallRequestHistoryService;


    @GetMapping
    public List<ApiCallRequestHistory> getAll() {
        return apiCallRequestHistoryService.getAll();
    }

    @PostMapping(value = "/buscar")
    public Page<ApiCallRequestHistory> buscar(@RequestParam("code") String code, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
                         @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir) {


        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        // Items fin

        Page<ApiCallRequestHistory> pagina = null;

        pagina = buscarPagina(code, currentPage, pageSize, sortField, sortDir);

        int totalPages = pagina.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());

        }

        return pagina;
    }

    private Page<ApiCallRequestHistory> buscarPagina(String code, int pageNo, int pageSize, String sortField, String sortDir) {


        return apiCallRequestHistoryService.search(code ,pageNo, pageSize, sortField, sortDir);
    }
}