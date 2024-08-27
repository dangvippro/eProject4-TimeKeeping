package com.timekeeping.timekeeping.controllers;//package com.timekeeping.timekeeping.controllers;
//
//import com.timekeeping.timekeeping.models.Job;
//import com.timekeeping.timekeeping.services.JobService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/jobs")
//public class JobController {
//
//    @Autowired
//    private JobService jobService;
//
//    @GetMapping
//    public String listJobs(Model model) {
//        List<Job> jobs = jobService.findAll();
//        model.addAttribute("jobs", jobs);
//        return "job-list";
//    }
//
//
//    @GetMapping("/job-form")
//    public String showJobForm(Model model) {
//        model.addAttribute("job", new Job());
//        return "job-form";
//    }
//
//    @PostMapping("/save")
//    public String saveJob(@ModelAttribute Job job) {
//        jobService.save(job);
//        return "redirect:/jobs";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String editJob(@PathVariable Long id, Model model) {
//        Job job = jobService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid job Id:" + id));
//        model.addAttribute("job", job);
//        return "job-edit";
//    }
//
//    @PostMapping("/update/{id}")
//    public String updateJob(@PathVariable Long id, @ModelAttribute Job job) {
//        job.setId(id);
//        jobService.save(job);
//        return "redirect:/jobs";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteJob(@PathVariable Long id) {
//        jobService.deleteById(id);
//        return "redirect:/jobs";
//    }
//
//
//}