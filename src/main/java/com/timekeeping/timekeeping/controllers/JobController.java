package com.timekeeping.timekeeping.controllers;

import com.timekeeping.timekeeping.models.Job;
import com.timekeeping.timekeeping.models.Recruitment;
import com.timekeeping.timekeeping.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("/list")
    public String listJobs(Model model) {
        List<Job> jobs = jobService.findAll();
        model.addAttribute("jobs", jobs);
        return "jobs/job-list";
    }


    @GetMapping("/new")
    public String showJobForm(Model model) {
        model.addAttribute("job", new Job());
        return "/jobs/job-form";
    }

    @PostMapping("/save")
    public String saveJob(@ModelAttribute Job job, @RequestParam("image") MultipartFile file) {
        System.out.println("Received recruitment: " + job);
        System.out.println("File Name: " + file.getOriginalFilename());
        System.out.println("File Size: " + file.getSize());

        jobService.save(job);
        return "redirect:/jobs/list";
    }

    @GetMapping("/edit/{jobID}")
    public String editJob(@PathVariable int jobID, Model model) {
        Optional<Job> jobs = jobService.findById(jobID);
        model.addAttribute("jobs", jobs);
        return "jobs/job-edit";
    }

    @PostMapping("/update")
    public String updateJob( @ModelAttribute Job job) {
        jobService.save(job);
        return "redirect:/jobs/list";
    }

    @GetMapping("/delete/{jobID}")
    public String deleteJob(@PathVariable int jobID) {
        jobService.deleteById(jobID);
        return "redirect:/jobs/list";
    }

    @ControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public String handleValidationExceptions(MethodArgumentNotValidException ex, Model model) {
            model.addAttribute("error", "Validation failed: " + ex.getMessage());
            return "error";
        }

        @ExceptionHandler(IOException.class)
        public String handleIOException(IOException ex, Model model) {
            model.addAttribute("error", "File handling error: " + ex.getMessage());
            return "error";
        }
    }


}