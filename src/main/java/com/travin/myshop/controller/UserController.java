package com.travin.myshop.controller;

import com.travin.myshop.domain.Role;
import com.travin.myshop.domain.User;
import com.travin.myshop.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
    @GetMapping("{user}")
    public String userList(@RequestParam User user, Model model) {
        Iterable<Role> roles = List.of(Role.values());
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "user-edit";
    }
}
