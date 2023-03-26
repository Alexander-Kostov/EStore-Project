package com.example.EStore.web;

import com.example.EStore.model.dto.AddProductDTO;
import com.example.EStore.model.entity.ProductEntity;
import com.example.EStore.service.ImageService;
import com.example.EStore.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;

@Controller
public class AddProductController {

    private ImageService imageService;

    private ProductService productService;

    public AddProductController(ImageService imageService, ProductService productService) {
        this.imageService = imageService;
        this.productService = productService;
    }

    @GetMapping("/add-product")
    public String addProduct(Model model) {

        if (!model.containsAttribute("addProductDTO")) {
            model.addAttribute("addProductDTO", new AddProductDTO());
        }
        return "product-add";
    }

    @PostMapping("/product-add")
    public String addProduct(@Valid AddProductDTO addProductDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                             Principal principal) throws IOException {

        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(addProductDTO.getName())
                .setColour(addProductDTO.getColour())
                .setAvailable(true);

        this.productService.createProduct(productEntity, addProductDTO.getImage());



        return "redirect:/products";
    }
}
