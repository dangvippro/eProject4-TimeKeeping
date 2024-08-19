package com.timekeeping.timekeeping.controllers;

import com.timekeeping.timekeeping.models.Account;
import com.timekeeping.timekeeping.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public String getAllEmployee(Model model) {
        model.addAttribute("accounts", accountService.findAll());
        return "employee/index";
    }
    @GetMapping("/id")
    public String getEmployeeById(@PathVariable int id, Model model) {
        Optional<Account> account = accountService.findById(id);
        if (account.isPresent()){
            model.addAttribute("account", account.get());
            return "employee/detail";
        }else {
            return "redirect:/employee";
        }
    }
    @GetMapping("/create")
    public String createEmployee(Model model) {
        model.addAttribute("account", new Account());
        return "employee/create";
    }
    @PostMapping("/create")
    public String createEmployee(@ModelAttribute Account account) {
        accountService.save(account, account.getRoleId());
        return "redirect:/employee";
    }
    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable int id, Model model) {
        Optional<Account> account = accountService.findById(id);
        if (account.isPresent()){
            model.addAttribute("account", account.get());
            return "employee/edit";
        }else {
            return "redirect:/employee";
        }
    }
    @PostMapping("/edit/{id}")
    public String editEmployee(@PathVariable int id, @ModelAttribute Account account) {
        account.setAccountID(id);
        accountService.save(account, account.getRoleId());
        return "redirect:/employee";
    }
    @GetMapping("/delete")
    public String deleteEmployee(@PathVariable int id) {
        accountService.delete(id);
        return "redirect:/employee";
    }
}
