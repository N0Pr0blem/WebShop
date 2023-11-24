package com.travin.myshop.controller;

import com.travin.myshop.domain.Product;
import com.travin.myshop.exception.InputDataException;
import com.travin.myshop.repos.ProductRepository;
import com.travin.myshop.service.ProductService;
import com.travin.myshop.service.UserService;
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
    @Autowired
    ProductService productService;

    @GetMapping
    public String toMainAdminPage(Model model) {
        return "redirect:/home";
    }


    @GetMapping("/add")
    public String hub(Model model) {
        return "add-product";
    }

    @PostMapping("/add")
    public String add(
            @RequestParam String name,
            @RequestParam String price,
            @RequestParam String count,
            @RequestParam String company,
            @RequestParam String description,
            @RequestParam String image,
            Model model
    ) {
        try {
            productService.addNewProduct(name, price, count, company, description, image);
        } catch (InputDataException inputDataException) {
            model.addAttribute("message", inputDataException.getMessage());
        } finally {
            return "redirect:/home";
        }
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
            @RequestParam String company,
            @RequestParam String description,
            @RequestParam String price,
            @RequestParam String count,
            @RequestParam String image,
            Model model
    ) {
        try {
            productService.updateProduct(product, name, price, company, description, count, image);
        } catch (InputDataException ex) {
            model.addAttribute("message", ex.getMessage());
        } finally {
            return "redirect:/home";
        }
    }

}
