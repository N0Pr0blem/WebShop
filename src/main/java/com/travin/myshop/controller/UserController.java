package com.travin.myshop.controller;

import com.travin.myshop.domain.Role;
import com.travin.myshop.domain.User;
import com.travin.myshop.exception.InputDataException;
import com.travin.myshop.exception.UserAlreadyExistException;
import com.travin.myshop.repos.UserRepository;
import com.travin.myshop.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user-list";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "user-edit";
    }

    @PostMapping()
    public String userSave(
            @RequestParam String username,
            @RequestParam String phone,
            @RequestParam String email,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user,
            Model model
    ) {
        try{
            userService.updateData(user,username,phone,email,form);
        }
        catch (InputDataException | UserAlreadyExistException inputDataException){
            model.addAttribute("message",inputDataException.getMessage());
        }
        catch(Exception ex){
            model.addAttribute("message",ex.toString());
        }
        finally {
            model.addAttribute("users", userService.getAllUsers());
            return "user-list";
        }
    }

}
