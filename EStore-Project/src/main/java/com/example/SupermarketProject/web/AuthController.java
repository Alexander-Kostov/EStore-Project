package com.example.SupermarketProject.web;

import com.example.SupermarketProject.mapper.UserMapper;
import com.example.SupermarketProject.model.dto.RegisterDTO;
import com.example.SupermarketProject.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController{


    private UserMapper userMapper;
    private AuthService authService;

    public AuthController(UserMapper userMapper, AuthService authService) {
        this.userMapper = userMapper;
        this.authService = authService;
    }

    @ModelAttribute("registerDTO")
    public RegisterDTO registerDTO(){
        return new RegisterDTO();
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @GetMapping("/register")
    public String register() {

        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid RegisterDTO registerDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        System.out.println();
        if(bindingResult.hasErrors() || !this.authService.register(registerDTO)){
            redirectAttributes.addFlashAttribute("registerDTO", registerDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerDTO", bindingResult);

            return "redirect:/register";
        }

        return "redirect:/login";
    }


}
