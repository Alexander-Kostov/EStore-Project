package com.example.EStore.web;

import com.example.EStore.model.entity.CartItemEntity;
import com.example.EStore.model.entity.UserEntity;
import com.example.EStore.model.views.ProductView;
import com.example.EStore.service.ProductService;
import com.example.EStore.service.ShoppingCartService;
import com.example.EStore.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProductController {

    private ProductService productService;

    private UserService userService;

    private ShoppingCartService cartService;

    public ProductController(ProductService productService, UserService userService, ShoppingCartService cartService) {
        this.productService = productService;
        this.userService = userService;
        this.cartService = cartService;
    }

    @GetMapping("/products")
    public String products(Model model, @AuthenticationPrincipal UserDetails principal) {

        List<ProductView> allProductsViews = this.productService.getAllProducts();
        model.addAttribute("ProductsViews", allProductsViews);

        if (principal != null) {
            UserEntity customer = this.userService.getUserByPrincipal(principal.getUsername());
            List<CartItemEntity> allCartItemsForCurrentUser = this.cartService.getAllCartItemsForCurrentUser(customer);
            model.addAttribute("itemsNumber", allCartItemsForCurrentUser.size());

            return "product-list";
        }

        model.addAttribute("itemsNumber", 0);

        return "product-list";
    }

    @GetMapping("/products/remove/{id}")
    public String removeProduct(@PathVariable("id") Long id) {

        this.productService.removeProductById(id);

        return "redirect:/products";
    }




}
