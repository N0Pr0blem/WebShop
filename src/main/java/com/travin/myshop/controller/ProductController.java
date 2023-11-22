package com.travin.myshop.controller;

import com.travin.myshop.domain.Product;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    @GetMapping("{product}")
    public String productInfo(@PathVariable Product product, Model model) {
        String login = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        model.addAttribute("product",product);
        model.addAttribute("login", login);
        return "product-info";
    }
}
