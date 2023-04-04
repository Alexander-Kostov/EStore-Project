package com.example.EStore.web;

import com.example.EStore.model.dto.CartProductDTO;
import com.example.EStore.model.dto.ProductDetailDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {

    @GetMapping()
    public String productsInCart(Model model) {

        return "cart";
    }


    @PostMapping("/product")
    public String productsInCart(CartProductDTO cartProductDTO) {


        return "redirect:/products";
    }

}
