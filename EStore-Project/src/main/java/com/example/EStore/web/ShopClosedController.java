package com.example.EStore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopClosedController {

    @GetMapping("/closed")
    public String closed() {


        return "shop-closed";
    }
}
