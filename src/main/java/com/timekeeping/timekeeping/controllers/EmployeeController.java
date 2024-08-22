package com.timekeeping.timekeeping.controllers;

import com.timekeeping.timekeeping.models.Account;
import com.timekeeping.timekeeping.models.Role;
import com.timekeeping.timekeeping.services.AccountService;
import com.timekeeping.timekeeping.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;


    @GetMapping
    public String getAllEmployee(Model model) {
        model.addAttribute("accounts", accountService.findAll());
        return "employees/index";
    }
    @GetMapping("/id")
    public String getEmployeeById(@PathVariable int id, Model model) {
        Optional<Account> account = accountService.findById(id);
        if (account.isPresent()){
            model.addAttribute("account", account.get());
            return "employees/detail";
        }else {
            return "redirect:/employees";
        }
    }
    @GetMapping("/create")
    public String createEmployee(Model model) {
        model.addAttribute("account", new Account());
        return "employees/create";
    }
    @PostMapping("/create")
    public String createEmployee(@ModelAttribute Account account) {
        accountService.save(account, account.getRoleId());
        return "redirect:/employees";
    }
    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable int id, Model model) {
        Optional<Account> account = accountService.findById(id);
        if (account.isPresent()){
            model.addAttribute("account", account.get());
            List<Role> roles = roleService.findAll(); // Populate roles
            model.addAttribute("roles", roles);
            return "employees/edit";
        } else {
            return "redirect:/employees";
        }
    }

    @PostMapping("/edit")
    public String editEmployee(@ModelAttribute Account account) {
        if (account.getPassword() != null && !account.getPassword().isEmpty()) {
            account.setPassword(passwordEncoder.encode(account.getPassword()));
        } else {
            Optional<Account> existingAccount = accountService.findById(account.getAccountID());
            if (existingAccount.isPresent()) {
                account.setPassword(existingAccount.get().getPassword());
            } else {
                return "employees/edit?error=Notfound";
            }
        }
        accountService.save(account, account.getRoleId());
        return "redirect:/employee";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@PathVariable int id) {
        accountService.delete(id);
        return "redirect:/employees";
    }
}
