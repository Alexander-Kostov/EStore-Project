package com.example.EStore.web;

import com.example.EStore.model.dto.ViewProductDTO;
import com.example.EStore.model.entity.ProductEntity;
import com.example.EStore.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductDetailsController {

    private ProductService productService;

    public ProductDetailsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products-details")
    public String productsDetail() {

        return "product-detail";
    }

    @GetMapping("/products-details/{id}")
    public String productsDetail(@PathVariable("id") Long id, Model model) {


        return "product-detail";
    }

}
