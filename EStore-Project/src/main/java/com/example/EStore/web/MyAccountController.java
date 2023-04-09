package com.example.EStore.web;

import com.example.EStore.model.entity.*;
import com.example.EStore.repository.OrderRepository;
import com.example.EStore.service.ShoppingCartService;
import com.example.EStore.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MyAccountController {

    private UserService userService;

    private ShoppingCartService cartService;

    private OrderRepository orderRepository;

    public MyAccountController(UserService userService, ShoppingCartService cartService, OrderRepository orderRepository) {
        this.userService = userService;
        this.cartService = cartService;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/my-account")
    public String myAccount(Model model, @AuthenticationPrincipal UserDetails principal) {

        UserEntity customer = this.userService.getUserByPrincipal(principal.getUsername());
        List<CartItemEntity> allCartItemsForCurrentUser = this.cartService.getAllCartItemsForCurrentUser(customer);
        model.addAttribute("itemsNumber", allCartItemsForCurrentUser.size());

        List<OrderEntity> customerOrders = this.orderRepository.findItemsByCustomerId(customer.getId());

        model.addAttribute("customerOrders", customerOrders);
        System.out.println();
        return "my-account";
    }
}
