//package com.donato.challenge.filter;
//
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.web.util.ContentCachingRequestWrapper;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class MyFilter extends OncePerRequestFilter {
//
//
//    @Override
//    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
//        // Creamos un objeto ContentCachingRequestWrapper para capturar el cuerpo de la solicitud
//        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
//
//        // Invocamos al siguiente filtro o controlador
//        filterChain.doFilter(requestWrapper, response);
//
//        // Obtenemos el cuerpo de la solicitud
//        byte[] requestPayload = requestWrapper.getContentAsByteArray();
//        String requestBody = new String(requestPayload);
//        // Aquí puedes hacer lo que necesites con el cuerpo de la solicitud
//    }
//}
