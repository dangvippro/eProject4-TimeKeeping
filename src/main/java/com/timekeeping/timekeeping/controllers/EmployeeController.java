package com.timekeeping.timekeeping.controllers;

import com.timekeeping.timekeeping.models.Account;
import com.timekeeping.timekeeping.models.Department;
import com.timekeeping.timekeeping.models.Position;
import com.timekeeping.timekeeping.models.Role;
import com.timekeeping.timekeeping.services.AccountService;
import com.timekeeping.timekeeping.services.DepartmentService;
import com.timekeeping.timekeeping.services.PositionService;
import com.timekeeping.timekeeping.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
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
    @Autowired
    private PositionService positionService;
    @Autowired
    private DepartmentService departmentService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Department.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                Optional<Department> department = Optional.ofNullable(departmentService.findById(Integer.parseInt(text)));
                setValue(department.orElse(null));
            }
        });

        binder.registerCustomEditor(Position.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                Optional<Position> position = Optional.ofNullable(positionService.findById(Integer.parseInt(text)));
                setValue(position.orElse(null));
            }
        });

        binder.registerCustomEditor(Role.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                Optional<Role> role = Optional.ofNullable(roleService.findById(Integer.parseInt(text)));
                setValue(role.orElse(null));
            }
        });
    }

    @GetMapping
    public String getAllEmployee(Model model) {
        model.addAttribute("accounts", accountService.findAll());
        return "employees/index";
    }

    @GetMapping("/{id}")
    public String getEmployeeById(@PathVariable int id, Model model) {
        Optional<Account> account = accountService.findById(id);
        if (account.isPresent()) {
            model.addAttribute("account", account.get());
            return "employees/detail"; // This should be the correct Thymeleaf view
        } else {
            return "redirect:/employee"; // Redirect if account not found
        }
    }


    @GetMapping("/create")
    public String createEmployee(Model model) {
        model.addAttribute("account", new Account());
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        List<Position> positions = positionService.findAll();
        model.addAttribute("positions", positions);
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        return "employees/create";
    }

    @PostMapping("/create")
    public String createEmployee(@ModelAttribute Account account) {
        accountService.save(account, account.getRole());
        return "redirect:/employee";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable int id, Model model) {
        Optional<Account> account = accountService.findById(id);
        if (account.isPresent()) {
            model.addAttribute("account", account.get());
            List<Role> roles = roleService.findAll();
            model.addAttribute("roles", roles);
            List<Position> positions = positionService.findAll();
            model.addAttribute("positions", positions);
            List<Department> departments = departmentService.findAll();
            model.addAttribute("departments", departments);
            return "employees/edit";
        } else {
            return "redirect:/employee";
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
                return "employee/edit?error=Notfound";
            }
        }
        accountService.save(account, account.getRole());
        return "redirect:/employee";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable int id) {
        accountService.delete(id);
        return "redirect:/employee";
    }
    @GetMapping("/activate/{id}")
    public String activateEmployee(@PathVariable int id) {
        accountService.turnOn(id);
        return "redirect:/employee";
    }

}

