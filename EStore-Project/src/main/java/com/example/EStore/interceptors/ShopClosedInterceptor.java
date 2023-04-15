package com.example.EStore.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalTime;

@Component
public class ShopClosedInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        if (requestURI.equals("/products")) {
            LocalTime now = LocalTime.now();
            if (now.isAfter(LocalTime.of(23,59,59)) || now.isBefore(LocalTime.of(10, 59, 59))) {
                response.sendRedirect("/closed");
            }
            return true;
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);

    }
}