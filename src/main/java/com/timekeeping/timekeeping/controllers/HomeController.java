package com.timekeeping.timekeeping.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
