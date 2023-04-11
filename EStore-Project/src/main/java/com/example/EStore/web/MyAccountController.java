package com.example.EStore.web;

import com.example.EStore.model.dto.ChangeAccountDetailsDTO;
import com.example.EStore.model.entity.*;
import com.example.EStore.repository.OrderRepository;
import com.example.EStore.service.ShoppingCartService;
import com.example.EStore.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        if(!model.containsAttribute("changeAcc")){
            model.addAttribute("changeAcc", new ChangeAccountDetailsDTO());
        }


        return "my-account";
    }

    @PatchMapping("/my-account/edit/details")
    public String myAccountChangeDetails(@Valid ChangeAccountDetailsDTO changeAccountDetailsDTO,
                                         BindingResult bindingResult,
                                         RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("changeAcc", changeAccountDetailsDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.changeAcc", bindingResult);

            return "redirect:/my-account";
        }



        return "redirect:/";
    }
}
