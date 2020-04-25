package com.happyhour.HappyHour.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthenticationController {

    @RequestMapping("owner-login")
    public String index(Model model) {
        model.addAttribute("title", "Owner Login");
        return "owner-login";
    }

}
