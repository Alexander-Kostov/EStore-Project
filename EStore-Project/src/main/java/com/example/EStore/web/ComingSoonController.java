package com.example.EStore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ComingSoonController {

    @GetMapping("/closed")
    public String closed() {


        return "coming-soon-page";
    }
}
