package com.timekeeping.timekeeping.controllers;//package com.timekeeping.timekeeping.controllers;
//
//import com.timekeeping.timekeeping.models.Candidate;
//import com.timekeeping.timekeeping.repositories.CandidateRepository;
//import com.timekeeping.timekeeping.services.CandidateService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/candidates")
//public class CandidateController {
//
//    @Autowired
//    private CandidateService candidateService;
//
//    @GetMapping
//    public String listCandidates(Model model) {
//        List<Candidate> candidates = candidateService.findAll();
//        model.addAttribute("candidates", candidates);
//        return "candidate-list";
//    }
//
//    @GetMapping("/new")
//    public String showCandidateForm(Model model) {
//        model.addAttribute("candidate", new Candidate());
//        return "candidate-form";
//    }
//
//    @PostMapping("/save")
//    public String saveCandidate(@ModelAttribute Candidate candidate, Model model) {
//        candidateService.save(candidate);
//        model.addAttribute("message", "Hồ sơ ứng viên đã được lưu!");
//        return "candidate-details";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String editCandidate(@PathVariable Long id, Model model) {
//        Candidate candidate = candidateService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid candidate Id:" + id));
//        model.addAttribute("candidate", candidate);
//        return "candidate-form";
//    }
//
//    @PostMapping("/update/{id}")
//    public String updateCandidate(@PathVariable Long id, @ModelAttribute Candidate candidate) {
//        candidate.setId(id);
//        candidateService.save(candidate);
//        return "redirect:/candidates";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteCandidate(@PathVariable Long id) {
//        candidateService.deleteById(id);
//        return "redirect:/candidates";
//    }
//
//    @GetMapping("/{id}")
//    public String viewCandidate(@PathVariable Long id, Model model) {
//        Candidate candidate = candidateService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid candidate Id:" + id));
//        model.addAttribute("candidate", candidate);
//        return "candidate-details";
//    }
//}
