package com.travin.myshop.controller;

import com.travin.myshop.domain.User;
import com.travin.myshop.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String userList(Model model) {
        Iterable<User> allUsers = userRepository.findAll();
        model.addAttribute("users", allUsers);
        return "userList";
    }
}
