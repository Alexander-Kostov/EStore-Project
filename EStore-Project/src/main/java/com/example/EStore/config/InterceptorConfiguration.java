package com.example.EStore.config;

import com.example.EStore.interceptors.BlackListInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
    private BlackListInterceptor ipBlacklistInterceptor;

    public InterceptorConfiguration(BlackListInterceptor blacklist) {
        this.ipBlacklistInterceptor = blacklist;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(ipBlacklistInterceptor);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}

