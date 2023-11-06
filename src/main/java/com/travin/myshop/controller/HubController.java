package com.travin.myshop.controller;

import com.travin.myshop.domain.Product;
import com.travin.myshop.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HubController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/hub")
    public String hub(Model model) {
        Iterable<Product> allProducts = productRepository.findAll();
        model.addAttribute("products", allProducts);
        return "hub";
    }

    @PostMapping("/hub")
    public String add(@RequestParam String name, @RequestParam String price, Model model) {
        Product product = new Product(name, price);
        Iterable<Product> products = productRepository.findAll();

        productRepository.save(product);
        model.addAttribute("products", products);

        return "hub";
    }

}