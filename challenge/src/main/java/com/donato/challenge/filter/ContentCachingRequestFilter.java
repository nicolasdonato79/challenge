package com.donato.challenge.filter;

import com.donato.challenge.entities.ApiCallRequestHistory;
import com.donato.challenge.service.interfaces.ApiCallRequestHistoryService;
import org.apache.commons.lang3.StringUtils;
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
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Async
public class ContentCachingRequestFilter extends OncePerRequestFilter {

    private final Integer max=254;

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

            Integer status = responseWrapper.getStatus();
            String method = requestWrapper.getMethod();
            String endpoint = requestWrapper.getRequestURI();
            Date timestamp = new Date();
            String request = getString(requestWrapper, requestBody);
            String response= getString(responseWrapper,responseBody);

            ApiCallRequestHistory apiCallHistory = new ApiCallRequestHistory(status, method, endpoint, request, response , timestamp);

            apiCallRequestHistoryService.saveCall(apiCallHistory);

        }


    }
    private String getString(ContentCachingResponseWrapper responseWrapper, byte[] requestBody) throws UnsupportedEncodingException {
        String request=new String(requestBody, responseWrapper.getCharacterEncoding());
        return getString(request);
    }
    private String getString(ContentCachingRequestWrapper requestWrapper, byte[] requestBody) throws UnsupportedEncodingException {
        String request=new String(requestBody, requestWrapper.getCharacterEncoding());
        return getString(request);
    }

    private String getString(String valor) {
        valor = StringUtils.isNotEmpty(valor) ? valor.length()>max? valor.substring(0,max) : valor :null;
        return valor;
    }

}