package com.travin.myshop.controller;

import com.travin.myshop.domain.User;
import com.travin.myshop.exception.InputDataException;
import com.travin.myshop.exception.UserAlreadyExistException;
import com.travin.myshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        try {
            userService.addUser(user);
            return "redirect:/login";
        } catch (InputDataException | UserAlreadyExistException myException) {
            model.addAttribute("message", myException.getMessage());
            return "registration";
        } catch (Exception ex) {
            model.addAttribute("message", ex.toString());
            return "registration";
        }

    }
}
