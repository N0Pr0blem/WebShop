package com.travin.myshop.controller;

import com.travin.myshop.domain.Product;
import com.travin.myshop.domain.User;
import com.travin.myshop.exception.AuthorizationException;
import com.travin.myshop.exception.InputDataException;
import com.travin.myshop.service.ProductService;
import com.travin.myshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;

    @PostMapping()
    public String getAccount(Model model, Principal principal) {
        try {
            model.addAttribute("user", userService.getUserByPrincipal(principal));
            model.addAttribute("isAdmin", userService.isAdminByPrincipal(principal));
        } catch (AuthorizationException ex) {
            model.addAttribute("message", ex.getMessage());
        } finally {
            return "account-info";
        }

    }

    @GetMapping
    public String redirectToHome(Model model) {
        return "redirect:/home";
    }

    @GetMapping("/cart")
    public String getCart(Model model, Principal principal) {
        try {
            model.addAttribute("items", userService.getCartByPrincipal(principal));
            model.addAttribute("isAdmin", userService.isAdminByPrincipal(principal));
        } catch (AuthorizationException ex) {
            model.addAttribute("message", ex.getMessage());
        } finally {
            return "user-cart";
        }
    }

    @PostMapping("/cart/delete")
    public String deleteProductFromCart(@RequestParam("productId") Product product, Principal principal, Model model) {
        try {
            userService.deleteProductFromPrincipalCart(principal, product);
        } catch (AuthorizationException ex) {
            model.addAttribute("message", ex.getMessage());
        } finally {
            return "redirect:/account/cart";
        }
    }

    @PostMapping("/cart/buy")
    public String buyProductFromCart(
            @RequestParam("productId") Product product,
            @RequestParam String str_count,
            Model model,
            Principal principal
    ) {
        try {
            userService.deleteProductFromPrincipalCart(principal, product);
            productService.buyProduct(product, str_count);
        } catch (InputDataException ex) {
            model.addAttribute("message", ex.getMessage());
        } finally {
            return "redirect:/account/cart";
        }
    }

    @PostMapping("/edit")
    public String editAccount(
            @RequestParam("userId") User user,
            @RequestParam String new_password,
            Model model,
            @RequestParam String confirm_password
    ) {
        try {
            userService.changePassword(user, new_password, confirm_password);

        } catch (InputDataException exception) {
            model.addAttribute("message", exception.getMessage());
        } finally {
            return "redirect:/account";
        }
    }
}
