package com.donato.challenge.interceptor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class LimitRequestInterceptor implements HandlerInterceptor {

    private int maxRequests = 3;
    private long requestIntervalMs = 60000; // 60 segundos
    private Map<String, Integer> requestCounts = new ConcurrentHashMap<>();
    private Map<String, Long> lastRequestTimes = new ConcurrentHashMap<>();

    private Map<String, Integer> externalServiceRequest= new ConcurrentHashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String clientKey = getClientKey(request);
//
//        Long lastTime=lastRequestTimes.get(clientKey);
//        if(lastTime!=null && lastTime+requestIntervalMs< System.currentTimeMillis()){
//            lastRequestTimes.remove(clientKey);
//            requestCounts.remove(clientKey);
//        }
//
//
//
//        if (requestCounts.containsKey(clientKey)) {
//            int count = requestCounts.get(clientKey);
//            if (count > maxRequests) {
//                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
//                response.getWriter().write("Demasiadas solicitudes. Por favor, intente de nuevo en " + (requestIntervalMs / 1000) + " segundos.");
//                response.getWriter().flush();
//                response.getWriter().close();
//                return false;
//            } else {
//                requestCounts.put(clientKey, count + 1);
//            }
//        } else {
//            requestCounts.put(clientKey, 1);
//        }
//
//
//
//
//
//
//        lastRequestTimes.put(clientKey, System.currentTimeMillis());
        return true;
    }

    private String getClientKey(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String forwardedFor = request.getHeader("X-Forwarded-For");
        if (forwardedFor != null) {
            return forwardedFor.split(",")[0] + "-" + remoteAddr;
        } else {
            return remoteAddr;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        String clientKey = getClientKey(request);
        if(externalServiceRequest.containsKey(clientKey) && response.getStatus()==(HttpStatus.OK.value())){
            externalServiceRequest.remove(clientKey);
        }

        if (externalServiceRequest.containsKey(clientKey)) {
            int count = externalServiceRequest.get(clientKey);
            if (count > maxRequests) {
                response.reset();
                response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
                response.getWriter().write("Error en servicio externo reiterado: se bloquea servicio");
                response.getWriter().flush();
                response.getWriter().close();

            } else {
                externalServiceRequest.put(clientKey, count + 1);
            }
        } else {
            externalServiceRequest.put(clientKey, 1);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String clientKey = getClientKey(request);
        long now = System.currentTimeMillis();
        Long lastRequestTime = lastRequestTimes.get(clientKey);
        if (lastRequestTime != null && now - lastRequestTime > requestIntervalMs) {
            requestCounts.remove(clientKey);
            lastRequestTimes.remove(clientKey);
        }
    }
}

