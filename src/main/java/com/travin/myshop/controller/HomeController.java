package com.travin.myshop.controller;

import com.travin.myshop.exception.AuthorizationException;
import com.travin.myshop.service.ProductService;
import com.travin.myshop.service.UserService;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {
    Logger logger = LogManager.getLogger(HomeController.class);
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;

    @GetMapping("/home")
    public String home(Model model, Principal principal) {

        try{
            String login = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            model.addAttribute("products", productService.getAllProducts());
            model.addAttribute("login", login);
            model.addAttribute("isAdmin", userService.isAdminByPrincipal(principal));
            logger.info(NumberUtils.isCreatable("5.06"));
        }catch(AuthorizationException authorizationException){
            model.addAttribute("message",authorizationException.getMessage());
        }
        return "home";
    }

    @GetMapping
    public String toHome(Model model) {
        return "redirect:/home";
    }

}