package com.example.EStore.web;

import com.example.EStore.model.entity.UserEntity;
import com.example.EStore.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AccountsManagerController {

    private UserService userService;

    public AccountsManagerController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/accounts-manager")
    public String accountManagerPage(Model model) {

        List<UserEntity> allAccounts = this.userService.getAllAccounts();

        model.addAttribute("accounts", allAccounts);
        return "account-manager";
    }
}
