package com.donato.challenge;

import com.donato.challenge.interceptor.ApiCallHistoryInterceptor;
import com.donato.challenge.repository.ApiCallRequestHistoryRepository;
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
        registry.addInterceptor(new ApiCallHistoryInterceptor(apiCallRequestHistoryRepository));
    }
}
