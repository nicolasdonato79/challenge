package com.donato.challenge.interceptor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

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

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String clientKey = getClientKey(request);
        //tengo que limpiar el mapa si ya pasaron los 60 segundos
        Long lastTime=lastRequestTimes.get(clientKey);
        if(lastTime!=null && lastTime+requestIntervalMs< System.currentTimeMillis()){
            lastRequestTimes.remove(clientKey);
            requestCounts.remove(clientKey);
        }

        if (requestCounts.containsKey(clientKey)) {
            int count = requestCounts.get(clientKey);
            if (count > maxRequests) {
                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                response.getWriter().write("Demasiadas solicitudes. Por favor, intente de nuevo en " + (requestIntervalMs / 1000) + " segundos.");
                response.getWriter().flush();
                response.getWriter().close();
                return false;
            } else {
                requestCounts.put(clientKey, count + 1);
            }
        } else {
            requestCounts.put(clientKey, 1);
        }
        lastRequestTimes.put(clientKey, System.currentTimeMillis());
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

