package com.happyhour.HappyHour.controllers;

import com.happyhour.HappyHour.data.HappyHourRepository;
import com.happyhour.HappyHour.data.OwnerRepository;
import com.happyhour.HappyHour.models.HappyHour;
import com.happyhour.HappyHour.models.HourData;
import com.happyhour.HappyHour.models.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping
public class HappyHourController {

    @Autowired
    HappyHourRepository happyHourRepository;

    @Autowired
    OwnerRepository ownerRepository;

    @GetMapping("results")
    public String displayAllHappyHours(Model model) {
        model.addAttribute("title", "Search Results");
        return "results";
    }

    @PostMapping("results")
    public String searchHH(Model model, @RequestParam String searchTerm){
        model.addAttribute("title", "Search Results");
        ArrayList<HappyHour> searchResults = HourData.searchHappyHour(searchTerm, happyHourRepository.findAll());
        ArrayList<String> resultAddresses = new ArrayList<>();

        for (HappyHour result : searchResults) {
            resultAddresses.add(result.getAddress());
        }

        model.addAttribute("searchTerm",searchTerm);
        model.addAttribute("happyHours",searchResults);
        model.addAttribute("addressList",resultAddresses);

        return "results";
    }

    @GetMapping("owner-home/{ownerId}")
    public String displayCreateHappyHourFormAndTable(Model model, @PathVariable int ownerId) {
        Optional<Owner> result = ownerRepository.findById(ownerId);
        if (result.isPresent()) {
            Owner owner = result.get();
            model.addAttribute("title", "You are signed in as " + owner.getUsername());
            model.addAttribute("happyHours", owner.getHappyHours());
        } else {
            model.addAttribute("title", "Owner Home");
        }

        model.addAttribute(new HappyHour());
        return "owner-home";
    }

    @PostMapping("owner-home/{ownerId}")
    public String processCreateHappyHourForm(@ModelAttribute HappyHour newHappyHour,
                                         Errors errors, Model model, @PathVariable int ownerId) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Owner Home");
            return "owner-home";
        }
        Optional<Owner> result = ownerRepository.findById(ownerId);
        newHappyHour.setOwner(result.get());
        happyHourRepository.save(newHappyHour);
        return "redirect:/owner-home/" + ownerId;
    }

}
