package com.happyhour.HappyHour.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrationController {

    @GetMapping("owner-registration")
    public String index(Model model) {
        model.addAttribute("title","Owner Registration");
        return "owner-registration";
    }
}
