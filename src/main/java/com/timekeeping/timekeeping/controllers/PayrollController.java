package com.timekeeping.timekeeping.controllers;

import com.timekeeping.timekeeping.models.Payroll;
import com.timekeeping.timekeeping.services.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/payroll")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    @GetMapping
    public String getAllPayrolls(@RequestParam(value = "name", required = false) String name, Model model) {
        List<Payroll> payrolls;

        if (name != null && !name.isEmpty()) {
            payrolls = payrollService.findByName(name); // Assuming you're searching by employee name or payroll name
        } else {
            payrolls = payrollService.findAllPayrolls();
        }

        model.addAttribute("payrolls", payrolls);
        return "payroll/index"; // Assuming you have a Thymeleaf template named index.html under the "payroll" directory
    }


    @GetMapping("/{id}")
    public String getPayroll(@PathVariable int id, Model model) {
        Optional<Payroll> payroll = Optional.ofNullable(payrollService.findPayrollById(id));
        if (payroll.isPresent()) {
            model.addAttribute("payroll", payroll.get());
            return "payroll/detail";
        } else {
            return "redirect:/payroll";
        }
    }

    @GetMapping("/edit/{id}")
    public String editPayrollForm(@PathVariable int id, Model model) {
        Optional<Payroll> payroll = Optional.ofNullable(payrollService.findPayrollById(id));
        if (payroll.isPresent()) {
            model.addAttribute("payroll", payroll.get());
            return "payroll/edit";
        } else {
            return "redirect:/payroll";
        }
    }

    @PostMapping("/edit")
    public String editPayroll(@ModelAttribute Payroll payroll) {
        payrollService.updatePayroll(payroll);
        return "redirect:/payroll";
    }
}
