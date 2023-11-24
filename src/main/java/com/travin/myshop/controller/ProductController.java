package com.travin.myshop.controller;

import com.travin.myshop.domain.Product;
import com.travin.myshop.domain.User;
import com.travin.myshop.repos.ProductRepository;
import com.travin.myshop.repos.UserRepository;
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
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("{product}")
    public String productInfo(@PathVariable Product product, Model model) {
        String login = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        model.addAttribute("product", product);
        model.addAttribute("login", login);
        return "product-info";
    }

    @PostMapping("{product}/buy")
    public String buyProduct(
            @RequestParam("productId") Product product,
            @RequestParam Integer count,
            Principal principal,
            Model model
    ) {
        User user = userRepository.findByUsername(principal.getName());
        if (product.getCount() > count && count > 0 && count != null) {
            product.setCount(count);
            user.getCart().add(product);
            userRepository.save(user);
        }
        return "redirect:/home";
    }
}
