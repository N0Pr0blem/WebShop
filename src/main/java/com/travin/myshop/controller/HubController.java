package com.travin.myshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class HubController {

    @GetMapping
    public String hub(
            @RequestParam(name="name", defaultValue = "World") String name,
            Map<String, Object> model) {
        model.put("name",name);
        return "hub";
    }

}