package com.travin.myshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HubController {

    @GetMapping
    public String hub(Model model) {
        return "hub";
    }

}