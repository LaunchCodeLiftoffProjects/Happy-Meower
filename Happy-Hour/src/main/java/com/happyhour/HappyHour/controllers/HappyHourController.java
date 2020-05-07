package com.happyhour.HappyHour.controllers;

import com.happyhour.HappyHour.data.HappyHourRepository;
import com.happyhour.HappyHour.models.HappyHour;
import com.happyhour.HappyHour.models.HourData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping
public class HappyHourController {

    @Autowired
    private HappyHourRepository happyHourRepository;

    @GetMapping("results")
    public String displayAllHappyHours(Model model) {
        model.addAttribute("title", "Search Results");
        return "results";
    }

    @PostMapping("results")
    public String searchHH(Model model, @RequestParam String searchTerm){
        model.addAttribute("searchTerm",searchTerm);
        model.addAttribute("happyHours",HourData.searchHappyHour(searchTerm, happyHourRepository.findAll()));
        return "results";
    }

    @GetMapping("owner-home")
    public String displayCreateHappyHourFormAndTable(Model model) {
        model.addAttribute("title", "Owner Home");
        model.addAttribute("happyhours", happyHourRepository.findAll());
        model.addAttribute(new HappyHour());
        return "owner-home";
    }

    @PostMapping("owner-home")
    public String processCreateHappyHourForm(@ModelAttribute @Valid HappyHour newHappyHour,
                                         Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Owner Home");
            return "owner-home";
        }

        happyHourRepository.save(newHappyHour);
        return "owner-home";
    }

}
