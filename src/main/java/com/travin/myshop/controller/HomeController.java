package com.travin.myshop.controller;

import com.travin.myshop.domain.Product;
import com.travin.myshop.repos.ProductRepository;
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

    @GetMapping("/home")
    public String home(Model model) {
        Iterable<Product> allProducts = productRepository.findAll();
        String login = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        model.addAttribute("products", allProducts);
        model.addAttribute("login", login);
        return "home";
    }
    @GetMapping
    public String toHome(Model model) {
        return "redirect:/home";
    }

    @GetMapping("/hub")
    public String hub(Model model) {
        Iterable<Product> allProducts = productRepository.findAll();
        model.addAttribute("products", allProducts);
        return "hub";
    }

    @PostMapping("/hub")
    public String add(@RequestParam String name, @RequestParam String price,@RequestParam Integer count, @RequestParam String image, Model model) {
        Product product = new Product(name, price,count,image);
        Iterable<Product> products = productRepository.findAll();

        productRepository.save(product);
        model.addAttribute("products", products);

        return "hub";
    }

}