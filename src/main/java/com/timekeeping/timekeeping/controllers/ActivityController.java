package com.timekeeping.timekeeping.controllers;

import com.timekeeping.timekeeping.models.Activity;
import com.timekeeping.timekeeping.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/activities")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @GetMapping
    public String getAllActivities(Model model) {
        model.addAttribute("activities", activityService.getAllActivities());
        model.addAttribute("activity", new Activity());
        return "activities/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("activity", new Activity());
        return "activities/create";
    }

    @GetMapping("/edit/{id}")
    public String editActivity(@PathVariable int id, Model model) {
        Optional<Activity> activity = activityService.getActivityById(id);
        if (activity.isPresent()) {
            model.addAttribute("activity", activity.get());
            return "activities/edit";
        }
        return "redirect:/activities";
    }

    @PostMapping
    public String saveActivity(@ModelAttribute("activity") Activity activity) {
        activityService.saveActivity(activity);
        return "redirect:/activities";
    }

    @GetMapping("/delete/{id}")
    public String deleteActivity(@PathVariable int id) {
        activityService.deleteActivity(id);
        return "redirect:/activities";
    }

    @GetMapping("/find")
    public String findByActivityName(@RequestParam("activityName") String activityName, Model model) {
        model.addAttribute("activities", activityService.findByActivityName(activityName));
        return "activities/index";
    }
}