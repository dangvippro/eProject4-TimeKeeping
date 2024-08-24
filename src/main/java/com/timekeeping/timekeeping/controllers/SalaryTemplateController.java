package com.timekeeping.timekeeping.controllers;

import com.timekeeping.timekeeping.models.SalaryTemplate;
import com.timekeeping.timekeeping.services.RegionService;
import com.timekeeping.timekeeping.services.SalaryTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/salaryTemplates")
public class SalaryTemplateController {

    @Autowired
    private SalaryTemplateService salaryTemplateService;
    @Autowired
    private RegionService regionService;

    @GetMapping
    public String getAllSalaryTemplates(Model model) {
        List<SalaryTemplate> salaryTemplates = salaryTemplateService.findAllSalaryTemplates();
        model.addAttribute("salaryTemplates", salaryTemplates);
        return "salaryTemplates/index"; // Assuming you have a Thymeleaf template named index.html under the "salaryTemplates" directory
    }

    @GetMapping("/{id}")
    public String getSalaryTemplateById(@PathVariable int id, Model model) {
        Optional<SalaryTemplate> salaryTemplate = salaryTemplateService.findSalaryTemplateById(id);
        if (salaryTemplate.isPresent()) {
            model.addAttribute("salaryTemplate", salaryTemplate.get());
            return "salaryTemplates/detail";
        } else {
            return "redirect:/salaryTemplates";
        }
    }

    @GetMapping("/create")
    public String createSalaryTemplateForm(Model model) {
        model.addAttribute("salaryTemplate", new SalaryTemplate());
        model.addAttribute("regions", regionService.findAll()); // Add regions to model
        return "salaryTemplates/create";
    }

    @PostMapping("/create")
    public String createSalaryTemplate(@ModelAttribute SalaryTemplate salaryTemplate) {
        salaryTemplateService.createSalaryTemplate(salaryTemplate);
        return "redirect:/salaryTemplates";
    }

    @GetMapping("/edit/{id}")
    public String editSalaryTemplateForm(@PathVariable int id, Model model) {
        Optional<SalaryTemplate> salaryTemplate = salaryTemplateService.findSalaryTemplateById(id);
        if (salaryTemplate.isPresent()) {
            model.addAttribute("salaryTemplate", salaryTemplate.get());
            model.addAttribute("regions", regionService.findAll());
            return "salaryTemplates/edit";
        } else {
            return "redirect:/salaryTemplates";
        }
    }

    @PostMapping("/edit")
    public String editSalaryTemplate(@ModelAttribute SalaryTemplate salaryTemplate) {
        salaryTemplateService.updateSalaryTemplate(salaryTemplate);
        return "redirect:/salaryTemplates";
    }

    @GetMapping("/delete/{id}")
    public String deleteSalaryTemplate(@PathVariable int id) {
        salaryTemplateService.deleteSalaryTemplate(id);
        return "redirect:/salaryTemplates";
    }
}
