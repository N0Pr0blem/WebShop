package com.travin.myshop.controller;

import com.travin.myshop.domain.Product;
import com.travin.myshop.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @GetMapping("{product}")
    public String productInfo(@PathVariable Product product, Model model) {
        String login = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        model.addAttribute("product", product);
        model.addAttribute("login", login);
        return "product-info";
    }

    @PostMapping("{product}/buy")
    public String buyProduct(@RequestParam("productId") Product product, @RequestParam Integer count, Model model) {
        if (product.getCount() > count && count > 0 && count != null) {
            product.setCount(product.getCount() - count);
            productRepository.save(product);
        }
        return "redirect:/home";
    }
}
