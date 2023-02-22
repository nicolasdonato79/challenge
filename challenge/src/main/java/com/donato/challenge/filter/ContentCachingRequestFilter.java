package com.donato.challenge.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Component
public class ContentCachingRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        filterChain.doFilter(requestWrapper, responseWrapper);

        byte[] requestBody = requestWrapper.getContentAsByteArray();
        if (requestBody.length > 0) {
            System.out.println(("Request body: {}" + new String(requestBody, requestWrapper.getCharacterEncoding())));
        }
        byte[] responseBody = responseWrapper.getContentAsByteArray();
        if (responseBody.length > 0) {
            System.out.println(("Response body: {}" + new String(responseBody, responseWrapper.getCharacterEncoding())));

        }

    }
}