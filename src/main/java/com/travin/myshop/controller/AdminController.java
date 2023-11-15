package com.travin.myshop.controller;

import com.travin.myshop.domain.Product;
import com.travin.myshop.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/admin")
@Controller
public class AdminController {
    @Autowired
    ProductRepository productRepository;
    @GetMapping("/home")
    public String mainAdminPage(Model model){
        Iterable<Product> allProducts = productRepository.findAll();
        model.addAttribute("products", allProducts);
        return "admin-home";
    }
    @GetMapping
    public String toMainAdminPage(Model model){
        return "redirect:/admin/home";
    }
    @GetMapping("/")
    public String againToMainAdminPage(Model model){
        return "redirect:/admin/home";
    }
    @GetMapping("/add")
    public String hub(Model model) {
        return "add-product";
    }

    @PostMapping("/add")
    public String add(@RequestParam String name, @RequestParam String price, @RequestParam Integer count, @RequestParam String image, Model model) {
        Product product = new Product(name, price,count,image);
        productRepository.save(product);
        return "admin/home";
    }
}
