package com.example.EStore.web;

import com.example.EStore.model.entity.CartItemEntity;
import com.example.EStore.model.entity.UserEntity;
import com.example.EStore.repository.UserRepository;
import com.example.EStore.service.ShoppingCartService;
import com.example.EStore.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.HttpResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class ContactUsController {

    private UserService userService;

    private ShoppingCartService cartService;

    private UserRepository userRepository;

    public ContactUsController(UserService userService, ShoppingCartService cartService, UserRepository userRepository) {
        this.userService = userService;
        this.cartService = cartService;
        this.userRepository = userRepository;
    }

    @GetMapping("/contact")
    public String contactPage(Model model, @AuthenticationPrincipal UserDetails principal) {

        UserEntity customer = this.userService.getUserByPrincipal(principal.getUsername());
        List<CartItemEntity> allCartItemsForCurrentUser = this.cartService.getAllCartItemsForCurrentUser(customer);

        model.addAttribute("itemsNumber", allCartItemsForCurrentUser.size());

        return "contact";
    }
}
