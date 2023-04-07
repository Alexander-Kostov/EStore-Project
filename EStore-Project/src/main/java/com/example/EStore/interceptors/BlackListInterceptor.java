package com.example.EStore.interceptors;

import com.example.EStore.service.BlackListService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.View;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import java.util.*;

@Component
public class BlackListInterceptor implements HandlerInterceptor {
    private ThymeleafViewResolver thymeleafViewResolver;

    private BlackListService service;

    public BlackListInterceptor(ThymeleafViewResolver thymeleafViewResolver, BlackListService service) {
        this.thymeleafViewResolver = thymeleafViewResolver;
        this.service = service;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler)
            throws Exception {
        String ip = getIpAddressFromRequest(request);

        RequestMatcher staticResourceRequestMatcher = PathRequest.toStaticResources().atCommonLocations();

        RequestMatcher imageMatcher = AntPathRequestMatcher.antMatcher("/img/**");

        if (!staticResourceRequestMatcher.matches(request) && service.isBlacklisted(ip) && !imageMatcher.matches(request)) {

            View blockedView = thymeleafViewResolver.resolveViewName("blacklisted.html", Locale.getDefault());

            if (blockedView != null) {
                blockedView.render(Map.of(), request, response);
            }
            return false;
        }
        return true;

    }

    private String getIpAddressFromRequest(HttpServletRequest request) {

        String ipAddress = null;

        String xffHeader = request.getHeader("X-Forwarded-For");
        if (xffHeader != null && !xffHeader.equals("unknown")) {
            int commaIdx = xffHeader.indexOf(",");
            if (commaIdx > 0) {
                ipAddress = xffHeader.substring(0, commaIdx - 1);
            }
        }

        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        return ipAddress;
    }
}
