package com.travin.myshop.controller;

import com.travin.myshop.domain.Product;
import com.travin.myshop.repos.ProductRepository;
import com.travin.myshop.repos.UserRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;
    Logger log = LogManager.getLogger(UserController.class);

    @GetMapping("/home")
    public String home(Model model,Principal principal) {
        Iterable<Product> allProducts = productRepository.findAll();
        model.addAttribute("products", allProducts);
        model.addAttribute("roles", userRepository.findByUsername(principal.getName()).getRoles());
        log.info(userRepository.findByUsername(principal.getName()).getRoles());
        return "home";
    }

    @GetMapping
    public String toHome(Model model) {
        return "redirect:/home";
    }


}