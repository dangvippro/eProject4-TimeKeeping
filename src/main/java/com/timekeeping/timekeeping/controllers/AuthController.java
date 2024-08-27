package com.timekeeping.timekeeping.controllers;

import com.timekeeping.timekeeping.models.Account;
import com.timekeeping.timekeeping.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("account", new Account());
        return "auth/login";
    }

    @PostMapping("/login")
    public String loginSubmit(Account account, Model model) {
        Optional<Account> foundAccountOptional  = accountService.findByUsernameAndPassword(account.getUsername(), account.getPassword());
        if (foundAccountOptional.isPresent()) {
            Account foundAccount = foundAccountOptional.get();

            return foundAccount.getRole().getRoleID() >= 1 || foundAccount.getRole().getRoleID() <= 3
                    ? "redirect:/admin/dasboard"
                    : "redirect:/home";
        } else {
            model.addAttribute("error", "Sai tài khoản hoặc mật khẩu.");
            return "auth/login";
        }
    }
    @GetMapping("/change-password")
    public String changePassword(Model model) {
        model.addAttribute("account", new Account());
        return "auth/change-password";
    }
    @PostMapping("/change-password")
    public String changePasswordSubmit(Account account,String newPassword, Model model) {
        Optional<Account> foundAccountOptional  = accountService.findByUsernameAndPassword(account.getUsername(), account.getPassword());
        if (foundAccountOptional.isPresent()) {
            Account foundAccount = foundAccountOptional.get();
            foundAccount.setPassword(newPassword);
            accountService.save(foundAccount, foundAccount.getRole());
            return "redirect:/auth";
        }else {
            model.addAttribute("error", "Mật khẩu không khớp với mật khẩu cũ!!");
            return "auth/change-password";
        }

    }
}
