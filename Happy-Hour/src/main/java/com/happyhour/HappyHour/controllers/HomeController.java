package com.happyhour.HappyHour.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("title", "Test Page");
        return "index";
    }




}