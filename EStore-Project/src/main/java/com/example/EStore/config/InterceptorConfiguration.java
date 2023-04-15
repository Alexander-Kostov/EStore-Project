package com.example.EStore.config;

import com.example.EStore.interceptors.BlackListInterceptor;
import com.example.EStore.interceptors.ShopClosedInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
    private BlackListInterceptor ipBlacklistInterceptor;

    private ShopClosedInterceptor shopClosedInterceptor;

    public InterceptorConfiguration(BlackListInterceptor blacklist, ShopClosedInterceptor shopClosedInterceptor) {
        this.ipBlacklistInterceptor = blacklist;
        this.shopClosedInterceptor = shopClosedInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(ipBlacklistInterceptor);
        registry.addInterceptor(shopClosedInterceptor);
        WebMvcConfigurer.super.addInterceptors(registry);
    }



}

