package com.example.EStore.config;

import com.example.EStore.interceptors.BlackListInterceptor;
import com.example.EStore.interceptors.ComingSoonInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
    private BlackListInterceptor ipBlacklistInterceptor;

    private ComingSoonInterceptor comingSoonInterceptor;

    public InterceptorConfiguration(BlackListInterceptor blacklist, ComingSoonInterceptor comingSoonInterceptor) {
        this.ipBlacklistInterceptor = blacklist;
        this.comingSoonInterceptor = comingSoonInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(ipBlacklistInterceptor);
        registry.addInterceptor(comingSoonInterceptor);
        WebMvcConfigurer.super.addInterceptors(registry);
    }



}

