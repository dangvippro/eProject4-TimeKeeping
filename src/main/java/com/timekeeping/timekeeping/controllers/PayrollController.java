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
    public String getAllPayrolls(Model model) {
        List<Payroll> payrolls = payrollService.findAllPayrolls();
        model.addAttribute("payrolls", payrolls);
        return "payroll/index";
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
