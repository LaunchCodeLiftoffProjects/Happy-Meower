package com.happyhour.HappyHour.controllers;

import com.happyhour.HappyHour.data.HappyHourRepository;
import com.happyhour.HappyHour.models.HappyHour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    HappyHourRepository happyHourRepository;

    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("title", "Home");
        model.addAttribute("dayOfWeek", DayOfWeek.values());
        model.addAttribute("happyHours", happyHourRepository.findTop5ByOrderByIdDesc());
        return "index";
    }

    @GetMapping("user-view/{hhId}")
    public String displayHH(Model model, @PathVariable int hhId){
        model.addAttribute("title", "User View");
        Optional optHH = happyHourRepository.findById(hhId);
        if (optHH.isPresent()) {
            HappyHour hh = (HappyHour) optHH.get();
            model.addAttribute("happyHour", hh);
            ArrayList<String> resultAddresses = new ArrayList<>();
            resultAddresses.add(hh.getAddress());
            model.addAttribute("addressList", resultAddresses);
            return "user-view";
        } else {
            return "redirect:../";
        }
    }
}