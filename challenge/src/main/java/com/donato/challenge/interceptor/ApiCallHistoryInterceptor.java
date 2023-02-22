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

import com.donato.challenge.repository.ApiCallRequestHistoryRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Component
public class ApiCallHistoryInterceptor implements AsyncHandlerInterceptor {
    private final ApiCallRequestHistoryRepository apiCallRequestHistoryRepository;


//    private final MyFilter myFilter = new MyFilter();


    public ApiCallHistoryInterceptor(ApiCallRequestHistoryRepository apiCallRequestHistoryRepository) {
        this.apiCallRequestHistoryRepository = apiCallRequestHistoryRepository;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Invocamos al siguiente filtro o controlador
//        Filter filterChain = new Filter() {
//            @Override
//            public void init(FilterConfig filterConfig) throws ServletException {
//                Filter.super.init(filterConfig);
//
//            }
//
//            @Override
//            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//                ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
//                ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
//
//                chain.doFilter(requestWrapper, responseWrapper);
//
//                byte[] requestBody = requestWrapper.getContentAsByteArray();
//                if (requestBody.length > 0) {
//                    System.out.println(("Request body: {}" + new String(requestBody, requestWrapper.getCharacterEncoding())));
//                }
//                byte[] responseBody = responseWrapper.getContentAsByteArray();
//                if (requestBody.length > 0) {
//                    System.out.println(("Response body: {}" + new String(responseBody, responseWrapper.getCharacterEncoding())));
//
//                }
//
//            }
//
//            @Override
//            public void destroy() {
//                Filter.super.destroy();
//            }
//
//        };
//        filterChain.doFilter(requestWrapper, response);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws IOException {

    }
}
