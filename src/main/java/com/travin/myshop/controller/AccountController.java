package com.travin.myshop.controller;

import com.travin.myshop.domain.Product;
import com.travin.myshop.domain.User;
import com.travin.myshop.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class AccountController {
    @Autowired
    UserRepository userRepository;
    @PostMapping("/account")
    public String getAccount(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user",user);

        return "account-info";
    }
}
