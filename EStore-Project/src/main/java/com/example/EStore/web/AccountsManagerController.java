package com.example.EStore.web;

import com.example.EStore.model.dto.ChangeUserAuthorityDTO;
import com.example.EStore.model.entity.UserEntity;
import com.example.EStore.model.enums.UserRoleEnum;
import com.example.EStore.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        model.addAttribute("changeAuthorityDTO", new ChangeUserAuthorityDTO());

        return "account-manager";
    }

    @PatchMapping("/accounts-manager/edit/{id}")
    public String accountManagerPage(@PathVariable("id") Long id, ChangeUserAuthorityDTO changeUserAuthorityDTO) {

        this.userService.updateRole(id, changeUserAuthorityDTO);


        return "redirect:/accounts-manager";
    }
}
