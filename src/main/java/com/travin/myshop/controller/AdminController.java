package com.travin.myshop.controller;

import com.travin.myshop.domain.Product;
import com.travin.myshop.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public String toMainAdminPage(Model model) {
        return "redirect:/home";
    }


    @GetMapping("/add")
    public String hub(Model model) {
        return "add-product";
    }

    @PostMapping("/add")
    public String add(@RequestParam String name, @RequestParam String price, @RequestParam Integer count, @RequestParam String image, Model model) {
        Product product = new Product(name, price, count, image);
        productRepository.save(product);
        return "/home";
    }

    @GetMapping("/product/{product}/edit")
    public String editForm(@PathVariable Product product, Model model) {
        model.addAttribute("product", product);
        return "product-edit";
    }

    @PostMapping("/product/{product}/edit")
    public String saveEdit(
            @RequestParam("productId") Product product,
            @RequestParam String name,
            @RequestParam String price,
            @RequestParam Integer count,
            @RequestParam String image,
            Model model
    ) {
        if(name!=null && price!=null && count!=null && image!=null){
            product.setName(name);
            product.setPrice(price);
            product.setCount(count);
            product.setImage(image);
            productRepository.save(product);
        }
        return "redirect:/home";
    }

}
