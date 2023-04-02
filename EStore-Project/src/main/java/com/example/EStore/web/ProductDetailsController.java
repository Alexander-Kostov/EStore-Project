package com.example.EStore.web;

import com.example.EStore.model.dto.ProductDetailDTO;
import com.example.EStore.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProductDetailsController {

    private ProductService productService;

    public ProductDetailsController(ProductService productService) {
        this.productService = productService;
    }

//    @GetMapping("/products-details")
//    public String productsDetail() {
//
//        return "product-detail";
//    }

    @GetMapping("/products-details/{id}")
    public String productsDetail(@PathVariable("id") Long id, Model model) {
        ProductDetailDTO productDetailDTO = this.productService.getProductById(id);
        List<String> allSizes = productDetailDTO.getSizes();
        List<String> allPictures = productDetailDTO.getThumbnailUrls();

        model.addAttribute("productDetailDTO",productDetailDTO);
        model.addAttribute("allPictures", allPictures);
        model.addAttribute("allSizes", allSizes);


        return "product-detail";
    }

}
