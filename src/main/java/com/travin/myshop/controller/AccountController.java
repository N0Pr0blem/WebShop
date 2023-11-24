package com.travin.myshop.controller;

import com.travin.myshop.domain.Role;
import com.travin.myshop.domain.User;
import com.travin.myshop.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    UserRepository userRepository;

    @PostMapping()
    public String getAccount(Model model, Principal principal) {
        boolean isAdmin;
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user", user);
        if (principal != null) {
            isAdmin = userRepository.findByUsername(principal.getName()).getRoles().contains(Role.ADMIN);
            model.addAttribute("isAdmin", isAdmin);
        }

        return "account-info";
    }

    @GetMapping("/cart")
    public String getCart(Model model, Principal principal){
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("items",user.getCart());
        return "user-cart";
    }
}
