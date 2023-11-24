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
    public String add(
            @RequestParam String name,
            @RequestParam Double price,
            @RequestParam Integer count,
            @RequestParam String company,
            @RequestParam String description,
            @RequestParam String image,
            Model model
    ) {
        Product product = new Product(name, price, company, description, count, image);
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
            @RequestParam String company,
            @RequestParam String description,
            @RequestParam Double price,
            @RequestParam Integer count,
            @RequestParam String image,
            Model model
    ) {
        try {
            if (!name.isEmpty() && price != null && count != null && image.isEmpty()) {
                product.setName(name);
                product.setPrice(price);
                product.setCompany(company);
                product.setDescription(description);
                product.setCount(count);
                product.setImage(image);
                productRepository.save(product);
            }
        }catch(Exception ex){

        }finally{
            return "redirect:/home";
        }
    }

}
