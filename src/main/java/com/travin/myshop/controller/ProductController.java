package com.travin.myshop.controller;

import com.travin.myshop.domain.Product;
import com.travin.myshop.domain.User;
import com.travin.myshop.repos.ProductRepository;
import com.travin.myshop.repos.UserRepository;
import com.travin.myshop.service.ProductService;
import com.travin.myshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    UserService userService;

    @GetMapping("{product}")
    public String productInfo(@PathVariable Product product, Model model) {
        String login = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        model.addAttribute("product", product);
        model.addAttribute("login", login);
        return "product-info";
    }

    @PostMapping("{product}/to_cart")
    public String buyProduct(
            @RequestParam("productId") Product product,
            Principal principal,
            Model model
    ) {
        userService.addToCart(product,principal);
        return "redirect:/home";
    }
}
