package com.travin.myshop.controller;

import com.travin.myshop.domain.Product;
import com.travin.myshop.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public String toMainAdminPage(Model model) {
        return "redirect:/admin/user";
    }


    @GetMapping("/add")
    public String hub(Model model) {
        return "add-product";
    }

    @PostMapping("/add")
    public String add(@RequestParam String name, @RequestParam String price, @RequestParam Integer count, @RequestParam String image, Model model) {
        Product product = new Product(name, price, count, image);
        productRepository.save(product);
        return "admin/home";
    }
}
