package com.timekeeping.timekeeping.controllers;

import com.timekeeping.timekeeping.models.Candidate;
import com.timekeeping.timekeeping.models.Recruitment;
import com.timekeeping.timekeeping.repositories.CandidateRepository;
import com.timekeeping.timekeeping.services.CandidateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;
    private static final String UPLOAD_DIR = "uploads/";

    @GetMapping("/list")
    public String listCandidates(Model model) {
        List<Candidate> candidates = candidateService.findAll();
        model.addAttribute("candidates", candidates);
        return "apply/candidate-list";
    }

    @GetMapping("/apply")
    public String showCandidateForm(Model model) {
        model.addAttribute("candidate", new Candidate());
        return "apply/apply-form";
    }



    @PostMapping("/apply")
    public String submitApplication(
            @ModelAttribute("candidate") @Valid Candidate candidate,
            BindingResult bindingResult,
            @RequestParam("resume") MultipartFile resume,
            @RequestParam("profilePicture") MultipartFile profilePicture,
            RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            return "apply-form";
        }

        // Process file uploads
        if (!resume.isEmpty()) {
            String resumePath = candidateService.saveFile(resume);
            candidate.setResume(resumePath);
        }

        if (!profilePicture.isEmpty()) {
            String profilePicturePath = candidateService.saveFile(profilePicture);
            candidate.setProfilePicturePath(profilePicturePath);
        }

        candidateService.save(candidate, resume, profilePicture);

        redirectAttributes.addFlashAttribute("message", "Application submitted successfully!");
        return "redirect:/apply-success";
    }



    @GetMapping("/apply-success")
    public String showSuccessPage(Model model) {
        return "apply/apply-success";
    }

    @GetMapping("/edit/{candidateID}")
    public String editCandidate(@PathVariable int candidateID, Model model) {
        Candidate candidate = candidateService.findById(candidateID).orElseThrow(() -> new IllegalArgumentException("Invalid candidate Id:" + candidateID));
        model.addAttribute("candidate", candidate);
        return "apply/candidate-form";
    }

    @PostMapping("/update/{candidateID}")
    public String updateCandidate(@PathVariable int candidateID,
                                  @ModelAttribute Candidate candidate,
                                  @RequestParam("resume") MultipartFile resume,
                                  @RequestParam("profilePicture") MultipartFile profilePicture) throws IOException {

        // Update candidateID
        candidate.setCandidateID(candidateID);

        // Check if there are new files and save them
        if (!resume.isEmpty()) {
            String resumePath = saveFile(resume);
            candidate.setResume(resumePath);
        }

        if (!profilePicture.isEmpty()) {
            String profilePicturePath = saveFile(profilePicture);
            candidate.setProfilePicturePath(profilePicturePath);
        }

        // Save the updated candidate
        candidateService.save(candidate, resume, profilePicture);

        // Redirect after successful update
        return "redirect:/candidates/list";
    }

    @GetMapping("/delete/{candidateID}")
    public String deleteCandidate(@PathVariable int candidateID) {
        candidateService.deleteById(candidateID);
        return "redirect:/candidates/list";
    }

    private String saveFile(MultipartFile file) {
        if (file.isEmpty()) {
            return null; // Handle empty files if needed
        }

        try {
            // Create the directory if it does not exist
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Generate a unique file name
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File destinationFile = new File(uploadDir, fileName);

            // Save the file to the server
            file.transferTo(destinationFile);

            // Return the path or URL of the saved file
            return UPLOAD_DIR + fileName;
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
            return null;
        }
    }}