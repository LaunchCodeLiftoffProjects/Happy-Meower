package com.happyhour.HappyHour.controllers;

import com.happyhour.HappyHour.data.HappyHourData;
import com.happyhour.HappyHour.models.HappyHour;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
public class HomeController {

    @RequestMapping("")
    public String index(Model model) {
        HappyHourData data=new HappyHourData();//instantiates temporary data
        model.addAttribute("title", "Test Page");
        model.addAttribute("happyHours",data.getHappyHours());
        return "index";
    }

    @GetMapping("user-view/{hhId}")
    public String displayHH(Model model, @PathVariable int hhId){
        HashMap<Integer, HappyHour> happyHours= HappyHourData.getHappyHours();
        model.addAttribute("happyHour",happyHours.get(hhId));
        return "user-view";

    }
}