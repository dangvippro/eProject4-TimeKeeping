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

    // Lấy danh sách tất cả các bảng lương
    @GetMapping
    public String getAllPayrolls(Model model) {
        List<Payroll> payrolls = payrollService.findAllPayrolls();
        model.addAttribute("payrolls", payrolls);
        return "payroll/index"; // Assuming you have a Thymeleaf template named index.html under the "payroll" directory
    }

    // Lấy chi tiết của một bảng lương theo ID
    @GetMapping("/{id}")
    public String getPayroll(@PathVariable int id, Model model) {
        Optional<Payroll> payroll = Optional.ofNullable(payrollService.findPayrollById(id));
        if (payroll.isPresent()) {
            model.addAttribute("payroll", payroll.get());
            return "payroll/detail"; // Assuming you have a Thymeleaf template named detail.html under the "payroll" directory
        } else {
            return "redirect:/payroll"; // Redirect if the payroll is not found
        }
    }

    // Hiển thị form tạo mới một bảng lương
    @GetMapping("/create")
    public String createPayrollForm(Model model) {
        model.addAttribute("payroll", new Payroll());
        return "payroll/create"; // Assuming you have a Thymeleaf template named create.html under the "payroll" directory
    }

    // Xử lý yêu cầu tạo mới một bảng lương
    @PostMapping("/create")
    public String createPayroll(@ModelAttribute Payroll payroll) {
        payrollService.createPayroll(payroll);
        return "redirect:/payroll";
    }

    // Hiển thị form chỉnh sửa một bảng lương
    @GetMapping("/edit/{id}")
    public String editPayrollForm(@PathVariable int id, Model model) {
        Optional<Payroll> payroll = Optional.ofNullable(payrollService.findPayrollById(id));
        if (payroll.isPresent()) {
            model.addAttribute("payroll", payroll.get());
            return "payroll/edit"; // Assuming you have a Thymeleaf template named edit.html under the "payroll" directory
        } else {
            return "redirect:/payroll";
        }
    }

    // Xử lý yêu cầu chỉnh sửa một bảng lương
    @PostMapping("/edit")
    public String editPayroll(@ModelAttribute Payroll payroll) {
        payrollService.updatePayroll(payroll);
        return "redirect:/payroll";
    }

    // Xóa một bảng lương
    @GetMapping("/delete/{id}")
    public String deletePayroll(@PathVariable int id) {
        payrollService.deletePayroll(id);
        return "redirect:/payroll";
    }
}
