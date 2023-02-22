package com.donato.challenge.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;

@Component
public class MasterFiltro extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("Request URI is: " + httpServletRequest.getRequestURI());
        filterChain.doFilter(httpServletRequest, httpServletResponse);
        System.out.println("Response Status Code is: " + httpServletResponse.getStatus());


        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(httpServletRequest);
        filterChain.doFilter(requestWrapper, httpServletResponse);


    }
}
