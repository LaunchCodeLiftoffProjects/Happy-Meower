package com.happyhour.HappyHour.controllers;

import com.happyhour.HappyHour.data.HappyHourData;
import com.happyhour.HappyHour.models.HappyHour;
import com.happyhour.HappyHour.models.HourData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping(value="results")
public class HappyHourController {

    @GetMapping
    public String displayAllHappyHours(Model model) {
        model.addAttribute("title", "Search Results");
        return "results";
    }

    @PostMapping
    public String searchHH(Model model, @RequestParam String searchTerm){
        ArrayList<HappyHour>temp=new ArrayList<>(HappyHourData.getHappyHours().values());
        model.addAttribute("searchTerm",searchTerm);
        model.addAttribute("happyHours",HourData.searchHappyHour(searchTerm, temp));
        return "results";
    }

}
