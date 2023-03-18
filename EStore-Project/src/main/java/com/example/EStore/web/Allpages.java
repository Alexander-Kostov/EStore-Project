package com.example.EStore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Allpages {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/home")
    public String homePage() {
        return "index";
    }

    @GetMapping("/products")
    public String products() {

        return "product-list";
    }

    @GetMapping("/products-details")
    public String productsDetail() {

        return "product-detail";
    }

    @GetMapping("/cart")
    public String cart() {

        return "cart";
    }

    @GetMapping("/checkout")
    public String checkout() {

        return "checkout";
    }

    @GetMapping("/my-account")
    public String myAccount() {

        return "my-account";
    }

    @GetMapping("/contact")
    public String contact() {

        return "contact";
    }

    @GetMapping("/wishlist")
    public String wishlist() {

        return "wishlist";
    }

    @GetMapping("/admins")
    public String adminsPage() {

        return "admins";
    }

    @GetMapping("/moderators")
    public String moderatorsPage() {

        return "moderators";
    }



}

