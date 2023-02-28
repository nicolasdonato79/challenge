package com.donato.challenge.filter;

import com.donato.challenge.entities.ApiCallRequestHistory;
import com.donato.challenge.service.interfaces.ApiCallRequestHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

            String request=new String(requestBody, requestWrapper.getCharacterEncoding());
            request=request.length()>255?request.substring(0,254):request;
            String response=new String(responseBody, responseWrapper.getCharacterEncoding()).substring(0,254);
            response=response.length()>255?response.substring(0,254):response;

            ApiCallRequestHistory apiCallHistory = new ApiCallRequestHistory(method, endpoint, request, response , timestamp);

            apiCallRequestHistoryService.saveCall(apiCallHistory);

        }


    }

}