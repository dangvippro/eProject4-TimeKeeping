package com.timekeeping.timekeeping.controllers;

import com.timekeeping.timekeeping.models.Job;
import com.timekeeping.timekeeping.services.JobService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    private static final Logger LOGGER = LoggerFactory.getLogger(JobController.class);

    @GetMapping("/list")
    public String listJobs(Model model) {
        List<Job> jobs = jobService.findAll();
        model.addAttribute("jobs", jobs);
        return "jobs/job-list";
    }

    @GetMapping("/new")
    public String showJobForm(Model model) {
        model.addAttribute("job", new Job());
        return "jobs/job-form";
    }

    @PostMapping("/save")
    public String saveJob(@ModelAttribute Job job, @RequestParam("image") MultipartFile imageFile, Model model) {
        try {
            // Handle file upload if a file is provided
            if (imageFile != null && !imageFile.isEmpty()) {
                String fileName = imageFile.getOriginalFilename();
                String uploadDir = "src/main/resources/static/images";

                // Create the upload directory if it doesn't exist
                File uploadDirFile = new File(uploadDir);
                if (!uploadDirFile.exists()) {
                    uploadDirFile.mkdirs();
                }

                // Save the file to the directory
                Path filePath = Paths.get(uploadDir, fileName);
                Files.write(filePath, imageFile.getBytes());

                // Set the image path in the Job object
                job.setImage("/images/" + fileName);
            }

            // Save job to the database
            jobService.save(job);
            return "redirect:/jobs/list";
        } catch (IOException e) {
            model.addAttribute("error", "File handling error: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/edit/{jobID}")
    public String editJob(@PathVariable int jobID, Model model) {
        Optional<Job> job = jobService.findById(jobID);
        if (job.isPresent()) {
            model.addAttribute("job", job.get());
            return "jobs/job-edit";
        } else {
            model.addAttribute("error", "Job not found");
            return "error";
        }
    }

    @PostMapping("/update")
    public String updateJob(@ModelAttribute Job job) {
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

    @Component
    public class DirectoryInitializer {

        @PostConstruct
        public void init() {
            // Define the directory path relative to the project root
            File directory = new File("uploads/images");

            // Create the directory if it does not exist
            if (!directory.exists()) {
                directory.mkdirs();
            }
        }
    }
}
