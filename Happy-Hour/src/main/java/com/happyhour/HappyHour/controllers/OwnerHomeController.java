package com.happyhour.HappyHour.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("owner-home")
public class OwnerHomeController {

    @GetMapping("")
    public String displayCreateNewHappyHourFormAndTable(Model model) {
        model.addAttribute("title", "Owner Home");
        return "owner-home";
    };


}
