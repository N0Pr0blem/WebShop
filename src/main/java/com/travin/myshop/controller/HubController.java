package com.travin.myshop.controller;

import com.travin.myshop.domain.Product;
import com.travin.myshop.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class HubController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public String hub(Map<String, Object> model) {
        Iterable<Product> allProducts = productRepository.findAll();
        model.put("products", allProducts);
        return "hub";
    }

    @PostMapping
    public String add(@RequestParam String name,@RequestParam String price, Map<String, Object> model){
        Product product = new Product(name,price);
        Iterable<Product> products = productRepository.findAll();

        productRepository.save(product);
        model.put("products", products);

        return "hub";
    }

}