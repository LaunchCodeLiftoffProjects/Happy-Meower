package com.happyhour.HappyHour.controllers;

import com.happyhour.HappyHour.data.DayTimeRepository;
import com.happyhour.HappyHour.data.HappyHourRepository;
import com.happyhour.HappyHour.data.OwnerRepository;
import com.happyhour.HappyHour.models.DayTime;
import com.happyhour.HappyHour.models.HappyHour;
import com.happyhour.HappyHour.models.HourData;
import com.happyhour.HappyHour.models.Owner;
import com.happyhour.HappyHour.models.dto.TimeFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.*;


@Controller
@RequestMapping
public class HappyHourController {

    @Autowired
    private HappyHourRepository happyHourRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private DayTimeRepository dayTimeRepository;

    @GetMapping("results")
    public String displayAllHappyHours(Model model) {
        model.addAttribute("title", "Search Results");
        return "results";
    }

    @PostMapping("results")
    public String searchHH(Model model, @RequestParam String searchTerm,  @RequestParam(required=false) DayOfWeek dayOfWeek){
        model.addAttribute("title", "Search Results");
        ArrayList<HappyHour> searchResults;
        ArrayList<String> resultAddresses = new ArrayList<>();
        List<HappyHour> tempHappyHour=new ArrayList<>();
        if(dayOfWeek!=null) {
            Set<HappyHour> tempDayTime= new LinkedHashSet<>();
            dayTimeRepository.findByDayOfWeek(dayOfWeek).forEach(dayTime -> tempDayTime.addAll(dayTime.getHappyHours()));
            tempHappyHour.addAll(tempDayTime);
            searchResults=HourData.searchHappyHour(searchTerm,tempHappyHour);
            model.addAttribute("dayOfWeek",dayOfWeek.getDisplayName(TextStyle.FULL,Locale.getDefault()));
        }else{
            searchResults=HourData.searchHappyHour(searchTerm,happyHourRepository.findAll());
        }
        for (HappyHour result : searchResults) {
            resultAddresses.add(result.getAddress());
        }
        if(searchResults.size()==0){
            model.addAttribute("none", "No results found");
        }
        model.addAttribute("searchTerm",searchTerm);
        model.addAttribute("happyHours",searchResults);
        model.addAttribute("addressList",resultAddresses);
        return "results";
    }

    @GetMapping("owner-home")
    public String displayCreateHappyHourFormAndTable(Model model, Principal principal) {
        SortedMap<Integer,String> displayTimes=new TreeMap<>();
        List<DayTime> tempDayTime=dayTimeRepository.findByDayOfWeek(DayOfWeek.SUNDAY);
        tempDayTime.forEach((dayTime)->displayTimes.put(dayTime.getTime(),HourData.getStandardTime(dayTime.getTime())));

        Owner result = ownerRepository.findByUsername(principal.getName());
        if (result!=null) {
            
            model.addAttribute("title", "You are signed in as " + result.getUsername());
            model.addAttribute("happyHours", result.getHappyHours());
        } else {
            model.addAttribute("title", "Owner Home");
        }

        model.addAttribute(new TimeFormDTO());
        model.addAttribute("dayOfWeek", DayOfWeek.values());
        model.addAttribute("displayTimes",displayTimes);
        model.addAttribute(new HappyHour());
        return "owner-home";
    }

    @PostMapping("owner-home")
    public String processCreateHappyHourForm(@ModelAttribute @Valid HappyHour newHappyHour, Errors errors, @ModelAttribute TimeFormDTO newTimeForm,
                                             Model model, Principal principal) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Owner Home");
            return "owner-home";
        }

        Owner result = ownerRepository.findByUsername(principal.getName());
        newHappyHour.setOwner(result);

        newHappyHour.setDayTimes(newTimeForm.getAllDayTimes(dayTimeRepository.findAll()));
        happyHourRepository.save(newHappyHour);
        return "redirect:/owner-home/";
    }

}
