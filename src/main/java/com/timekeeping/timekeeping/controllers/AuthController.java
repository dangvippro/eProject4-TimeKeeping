package com.timekeeping.timekeeping.controllers;

import com.timekeeping.timekeeping.models.Account;
import com.timekeeping.timekeeping.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AccountService accountService;

    @GetMapping({"", "/"})
    public String login(Model model) {
        model.addAttribute("account", new Account());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(Account account) {
        return "redirect:/home";
    }
}
