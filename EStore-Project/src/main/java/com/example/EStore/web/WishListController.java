package com.example.EStore.web;

import com.example.EStore.model.entity.CartItemEntity;
import com.example.EStore.model.entity.UserEntity;
import com.example.EStore.service.ShoppingCartService;
import com.example.EStore.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WishListController {

    private UserService userService;

    private ShoppingCartService cartService;

    public WishListController(UserService userService, ShoppingCartService cartService) {
        this.userService = userService;
        this.cartService = cartService;
    }

    @GetMapping("/wishlist")
    public String wishlist(Model model, @AuthenticationPrincipal UserDetails principal) {

        UserEntity customer = this.userService.getUserByPrincipal(principal.getUsername());
        List<CartItemEntity> allCartItemsForCurrentUser = this.cartService.getAllCartItemsForCurrentUser(customer);
        model.addAttribute("itemsNumber", allCartItemsForCurrentUser.size());

        return "coming-soon";
    }


}
