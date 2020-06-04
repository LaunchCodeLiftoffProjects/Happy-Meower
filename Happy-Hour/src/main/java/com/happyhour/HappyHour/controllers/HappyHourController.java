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
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;


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
    public String searchHH(Model model, @RequestParam String searchTerm, @RequestParam(required=false) DayOfWeek dayOfWeek){

        model.addAttribute("title", "Search Results");
        ArrayList<HappyHour> searchResults = HourData.searchHappyHour(searchTerm, happyHourRepository.findAll());
        ArrayList<String> resultAddresses = new ArrayList<>();

        for (HappyHour result : searchResults) {
            resultAddresses.add(result.getAddress());
        }
        model.addAttribute("searchTerm",searchTerm);
        //Will need optimization.
        if(searchTerm.equals("")){
            List<HappyHour> tempHappyHour=new ArrayList<>();
            List<DayTime> tempDayTime=dayTimeRepository.findByDayOfWeek(dayOfWeek);
            for (DayTime dayTime : tempDayTime) {
                if(!dayTime.getHappyHours().isEmpty()) {
                    List<HappyHour> a = dayTime.getHappyHours();
                    a.removeAll(tempHappyHour);
                    tempHappyHour.addAll(a);
                }
            }
            model.addAttribute("happyHours",tempHappyHour);
        }
        if(dayOfWeek==null){
            model.addAttribute("happyHours",HourData.searchHappyHour(searchTerm, happyHourRepository.findAll()));
        }
        if(dayOfWeek==null&& searchTerm.equals("")){
            return "redirect:";
        }
        model.addAttribute("addressList",resultAddresses);

        return "results";
    }

    @GetMapping("owner-home/{ownerId}")
    public String displayCreateHappyHourFormAndTable(Model model, @PathVariable int ownerId) {
        SortedMap<Integer,String> displayTimes=new TreeMap<>();
        List<DayTime> tempDayTime=dayTimeRepository.findByDayOfWeek(DayOfWeek.SUNDAY);
        tempDayTime.forEach((dayTime)->displayTimes.put(dayTime.getTime(),HourData.getStandardTime(dayTime.getTime())));

        Optional<Owner> result = ownerRepository.findById(ownerId);
        if (result.isPresent()) {
            Owner owner = result.get();
            model.addAttribute("title", "You are signed in as " + owner.getUsername());
            model.addAttribute("happyHours", owner.getHappyHours());
        } else {
            model.addAttribute("title", "Owner Home");
        }

        model.addAttribute(new TimeFormDTO());
        model.addAttribute("dayOfWeek", DayOfWeek.values());
        model.addAttribute("displayTimes",displayTimes);
        model.addAttribute(new HappyHour());
        return "owner-home";
    }

    @PostMapping("owner-home/{ownerId}")
    public String processCreateHappyHourForm(@ModelAttribute @Valid HappyHour newHappyHour, Errors errors, @ModelAttribute TimeFormDTO newTimeForm,
                                         Model model, @PathVariable int ownerId) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Owner Home");
            return "owner-home";
        }
        Optional<Owner> result = ownerRepository.findById(ownerId);
        newHappyHour.setOwner(result.get());
        newHappyHour.setDayTimes(newTimeForm.getAllDayTimes(dayTimeRepository.findAll()));
        happyHourRepository.save(newHappyHour);
        return "redirect:/owner-home/" + ownerId;
    }

}
