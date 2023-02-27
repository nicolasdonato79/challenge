package com.donato.challenge.filter;

import com.donato.challenge.entities.ApiCallRequestHistory;
import com.donato.challenge.service.interfaces.ApiCallRequestHistoryService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Async
public class ContentCachingRequestFilter extends OncePerRequestFilter {


    @Autowired
    private ApiCallRequestHistoryService apiCallRequestHistoryService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        filterChain.doFilter(requestWrapper, responseWrapper);
        registryCall(requestWrapper,responseWrapper);


    }

    private void registryCall(ContentCachingRequestWrapper requestWrapper, ContentCachingResponseWrapper responseWrapper) throws IOException {

        byte[] requestBody= requestWrapper.getContentAsByteArray();
        byte[] responseBody = responseWrapper.getContentAsByteArray();

        if (responseBody.length > 0) {
            responseWrapper.copyBodyToResponse();
            System.out.println(responseWrapper.getStatus());
            ApiCallRequestHistory callHistory= new ApiCallRequestHistory();
            callHistory.setEndpoint(requestWrapper.getMethod());
            String method = requestWrapper.getMethod();
            String endpoint = requestWrapper.getRequestURI();
            Date timestamp = new Date();

            ApiCallRequestHistory apiCallHistory = new ApiCallRequestHistory(method, endpoint, new String(requestBody, requestWrapper.getCharacterEncoding()), new String(responseBody, responseWrapper.getCharacterEncoding()), timestamp);

            apiCallRequestHistoryService.saveCall(apiCallHistory);

        }


    }

}