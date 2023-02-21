package com.donato.challenge.interceptor;

import com.donato.challenge.entities.ApiCallRequestHistory;
import com.donato.challenge.repository.ApiCallRequestHistoryRepository;
import com.donato.challenge.wrapper.CachedBodyHttpServletRequest;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class ApiCallHistoryInterceptor implements AsyncHandlerInterceptor {
    private final int contador=0;
    private final ApiCallRequestHistoryRepository apiCallRequestHistoryRepository;

    public ApiCallHistoryInterceptor(ApiCallRequestHistoryRepository apiCallRequestHistoryRepository) {
        this.apiCallRequestHistoryRepository = apiCallRequestHistoryRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
//        ContentCachingRequestWrapper req=null;
//        if(request.getContentLength() > 0){
//            req = new ContentCachingRequestWrapper(request);
//            byte[] requestBody = req.getContentAsByteArray();
//            if(requestBody.length==0){
//                System.out.println("no tiene nada");
//            }
//        }




//        String requestBody= req.getReader().lines().collect(Collectors.joining("\n"));
//        System.out.println("primero"+ requestBody);
        //ContentCachingResponseWrapper resp = new ContentCachingResponseWrapper(response);

        // Execution request chain
//        filterChain.doFilter(req, resp);


       // byte[] responseBody = resp.getContentAsByteArray();

//        System.out.println("request body = {}" + new String(requestBody, StandardCharsets.UTF_8));

//        log.info("response body = {}", new String(responseBody, StandardCharsets.UTF_8));

        // Finally remember to respond to the client with the cached data.
       // resp.copyBodyToResponse();


//        String method = request.getMethod();
//        String endpoint = request.getRequestURI();
//
////        String requestBody= new BufferedReader(new InputStreamReader(in)).lines().collect(Collectors.joining("\n"));
//        Date timestamp = new Date();
//
//        // Crear una instancia de la entidad de historial y guardarla en el repositorio
//        ApiCallRequestHistory apiCallHistory = new ApiCallRequestHistory(method, endpoint, null, null, timestamp);
//        apiCallRequestHistoryRepository.save(apiCallHistory);


//        CachedBodyHttpServletRequest cachedBodyHttpServletRequest = new CachedBodyHttpServletRequest(request);
//        String s=cachedBodyHttpServletRequest.getReader().readLine();
//        System.out.println(s);

        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

//       response.getOutputStream().println();


    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws IOException {
       //NO USAR


    }
}
