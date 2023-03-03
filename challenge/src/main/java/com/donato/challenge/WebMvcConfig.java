package com.donato.challenge;

import com.donato.challenge.controller.ApiRestControllerExceptionHandler;
import com.donato.challenge.interceptor.LimitRequestInterceptor;
import com.donato.challenge.repository.ApiCallRequestHistoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final ApiCallRequestHistoryRepository apiCallRequestHistoryRepository;

    public WebMvcConfig(ApiCallRequestHistoryRepository apiCallRequestHistoryRepository) {
        this.apiCallRequestHistoryRepository = apiCallRequestHistoryRepository;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LimitRequestInterceptor());
    }
    @Bean
    public ApiRestControllerExceptionHandler miManejadorDeExcepciones() {
        return new ApiRestControllerExceptionHandler();
    }

}
