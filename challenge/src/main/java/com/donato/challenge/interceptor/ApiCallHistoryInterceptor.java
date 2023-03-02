package com.donato.challenge.interceptor;

import java.io.IOException;

//import com.donato.challenge.filter.MyFilter;
//import jakarta.servlet.FilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.donato.challenge.repository.ApiCallRequestHistoryRepository;

import org.springframework.web.util.ContentCachingResponseWrapper;


@Component
public class ApiCallHistoryInterceptor implements AsyncHandlerInterceptor {
    private final ApiCallRequestHistoryRepository apiCallRequestHistoryRepository;


    public ApiCallHistoryInterceptor(ApiCallRequestHistoryRepository apiCallRequestHistoryRepository) {
        this.apiCallRequestHistoryRepository = apiCallRequestHistoryRepository;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws IOException {

    }
}
