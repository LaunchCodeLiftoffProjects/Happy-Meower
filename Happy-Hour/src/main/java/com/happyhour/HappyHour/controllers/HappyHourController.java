package com.happyhour.HappyHour.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="results")
public class HappyHourController {

    @GetMapping
    public String displayAllHappyHours(Model model) {
        model.addAttribute("title", "Search Results");
        return "results";
    }

}
