package com.timekeeping.timekeeping.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("title", "Home Page");
        return "home/index";
    }
    
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About");
        return "home/about";
    }
    
    @GetMapping("/service")
    public String service(Model model) {
        model.addAttribute("title", "Service");
        return "home/services";
    }
    
    @GetMapping("/blog")
    public String blog(Model model) {
        model.addAttribute("title", "Blog");
        return "home/blog";
    }
    
    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("title", "Contact");
        return "home/contact";
    }

    @GetMapping("/faceid")
    public String showFaceIdPage() {
        return "home/faceid"; // Ensure this matches the template name
    }
    @GetMapping("/attendance-success")
    public String attendanceSuccess(
            @RequestParam("name") String name,
            @RequestParam("age") String age,
            @RequestParam("time") String time,
            @RequestParam("date") String date,
            Model model) {

        model.addAttribute("name", name);
        model.addAttribute("age", age);
        model.addAttribute("time", time);
        model.addAttribute("date", date);

        return "home/attendance-success";
    }

    @GetMapping("/job-list")
    public String showJobPage() {
        return "home/job-list"; // Ensure this matches the template name
    }

}
