package com.timekeeping.timekeeping.controllers;

import com.timekeeping.timekeeping.models.Requestion;
import com.timekeeping.timekeeping.services.RequestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private RequestionService requestionService;
    @GetMapping("/requestion-create")
    public String createRequestion(Model model) {
        model.addAttribute("requestion", new Requestion());
        return "home/requestion-create";
    }
    @PostMapping("/requestion-create")
    public String createRequest(@ModelAttribute Requestion requestion, RedirectAttributes redirectAttributes) {
        requestion.setRequestDate(new Date());
        requestion.setStatus("Đang chờ phê duyệt");
        requestionService.createRequestion(requestion);
        // Add a flash attribute for success message
        redirectAttributes.addFlashAttribute("successMessage", "Tạo yêu cầu thành công");
        return "redirect:/requestion";
    }
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
