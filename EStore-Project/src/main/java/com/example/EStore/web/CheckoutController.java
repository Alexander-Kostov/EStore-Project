package com.example.EStore.web;

import com.example.EStore.model.dto.CheckoutDTO;
import com.example.EStore.model.entity.CartItemEntity;
import com.example.EStore.model.entity.UserEntity;
import com.example.EStore.service.OrderService;
import com.example.EStore.service.ShoppingCartService;
import com.example.EStore.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CheckoutController {

    private ShoppingCartService  cartService;
    private UserService userService;

    private OrderService orderService;

    public CheckoutController(ShoppingCartService cartService, UserService userService, OrderService orderService) {
        this.cartService = cartService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/checkout")
    public String checkout(Model model, @AuthenticationPrincipal UserDetails principal) {

        UserEntity customer = this.userService.getUserByPrincipal(principal.getUsername());

        int size = this.cartService.getAllCartItemsForCurrentUser(customer).size();

        List<CartItemEntity> cartItems = this.cartService.getAllCartItemsForCurrentUser(customer);

        double subTotal = this.cartService.sumAllProductsInCart(cartItems);
        double totalPrice = subTotal + 5;

        model.addAttribute("itemsNumber", size);
        model.addAttribute("subTotal", subTotal);
        model.addAttribute("totalPrice", totalPrice);

        if (!model.containsAttribute("checkoutDTO")) {
            model.addAttribute("checkoutDTO", new CheckoutDTO());
        }

        return "checkout";
    }

    @PostMapping("/checkout")
    public String checkout(@Valid CheckoutDTO checkoutDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetails principal) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("checkoutDTO", checkoutDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.checkoutDTO", bindingResult);

            return "redirect:checkout";
        }

        UserEntity customer = this.userService.getUserByPrincipal(principal.getUsername());

        List<CartItemEntity> allCartItemsForCurrentUser = this.cartService.getAllCartItemsForCurrentUser(customer);

        this.orderService.addOrderForCurrentCustomer(customer, allCartItemsForCurrentUser);

        return "redirect:my-account";
    }
}
