package com.example.EStore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlackListController {

    @GetMapping("/blacklisted")
    public String blacklist() {

        return "blacklisted";
    }
}
