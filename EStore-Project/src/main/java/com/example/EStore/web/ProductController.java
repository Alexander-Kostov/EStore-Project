package com.example.EStore.web;

import com.example.EStore.model.views.ProductView;
import com.example.EStore.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String products(Model model) {

        List<ProductView> allProductsViews = this.productService.getAllProducts();

        model.addAttribute("ProductsViews", allProductsViews);

        return "product-list";
    }


}
