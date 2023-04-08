package com.example.EStore.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class BlackListService {

    public boolean isBlacklisted(String ipAddress) {

        return ipAddress.startsWith("102") || ipAddress.startsWith("129") || ipAddress.startsWith("196");


    }

}

