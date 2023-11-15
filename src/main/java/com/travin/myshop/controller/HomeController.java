package com.travin.myshop.controller;

import com.travin.myshop.domain.Product;
import com.travin.myshop.repos.ProductRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @Autowired
    ProductRepository productRepository;
    Logger log = LogManager.getLogger(UserController.class);

    @GetMapping("/home")
    public String home(Model model) {
        Iterable<Product> allProducts = productRepository.findAll();
        String login = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        model.addAttribute("products", allProducts);
        model.addAttribute("login", login);
        log.info(login);
        return "home";
    }

    @GetMapping
    public String toHome(Model model) {
        return "redirect:/home";
    }

}