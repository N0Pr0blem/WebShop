package com.travin.myshop.controller;

import com.travin.myshop.domain.Product;
import com.travin.myshop.domain.Role;
import com.travin.myshop.domain.User;
import com.travin.myshop.repos.ProductRepository;
import com.travin.myshop.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.logging.Logger;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    @PostMapping()
    public String getAccount(Model model, Principal principal) {
        boolean isAdmin;
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user", user);
        if (principal != null) {
            isAdmin = userRepository.findByUsername(principal.getName()).getRoles().contains(Role.ADMIN);
            model.addAttribute("isAdmin", isAdmin);
        }

        return "account-info";
    }
    @GetMapping
    public String redirectToHome(Model model){
        return "redirect:/home";
    }

    @GetMapping("/cart")
    public String getCart(Model model, Principal principal){
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("items",user.getCart().getProducts());
        boolean isAdmin;
        if (principal != null) {
            isAdmin = userRepository.findByUsername(principal.getName()).getRoles().contains(Role.ADMIN);
            model.addAttribute("isAdmin", isAdmin);
        }
        return "user-cart";
    }
    @PostMapping("/cart/delete")
    public String deleteProductFromCart(@RequestParam("productId") Product product,Principal principal){
        User user = userRepository.findByUsername(principal.getName());
        user.getCart().getProducts().remove(product);
        userRepository.save(user);
        return"redirect:/account/cart";
    }
    @PostMapping("/cart/buy")
    public String buyProductFromCart(
            @RequestParam("productId") Product product,
            @RequestParam String str_count,
            Model model,
            Principal principal
    ){
        try{
            int count =Integer. parseInt(str_count);
            if(count>0 && count<=product.getCount()){
                User user = userRepository.findByUsername(principal.getName());
                user.getCart().getProducts().remove(product);
                product.setCount(product.getCount()-count);
                userRepository.save(user);
                productRepository.save(product);
            }
        }catch(Exception ex){
            model.addAttribute("message","Incorrect count");
        }
        finally {
            return"redirect:/account/cart";
        }
    }

    @PostMapping("/edit")
    public String editAccount(
            @RequestParam("userId") User user,
            @RequestParam String new_password,
            @RequestParam String confirm_password
    ){
        if(new_password.equals(confirm_password))
        {
            user.setPassword(new_password);
            userRepository.save(user);
        }
        return "redirect:/account";
    }
}
