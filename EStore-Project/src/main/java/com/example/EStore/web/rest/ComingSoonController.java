package com.example.EStore.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ComingSoonController {

    @GetMapping("/coming-soon")
    public String ComingSoonPage() {

        return "coming-soon";
    }
}
