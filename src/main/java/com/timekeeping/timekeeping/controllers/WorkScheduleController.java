package com.timekeeping.timekeeping.controllers;

import com.timekeeping.timekeeping.models.WorkSchedule;
import com.timekeeping.timekeeping.services.AccountService;
import com.timekeeping.timekeeping.services.ShiftService;
import com.timekeeping.timekeeping.services.WorkScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/workSchedules")
public class WorkScheduleController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private ShiftService shiftService;

    @Autowired
    private WorkScheduleService workScheduleService;

    @GetMapping
    public String getAllSchedules(Model model) {
        model.addAttribute("accounts", accountService.findAll());
        model.addAttribute("shifts", shiftService.getAllShifts());
        model.addAttribute("workSchedules", workScheduleService.getAllSchedules());
        model.addAttribute("workSchedule", new WorkSchedule());
        return "workSchedules/index";
    }

    @GetMapping
    public String create(Model model) {
        model.addAttribute("workSchedule", new WorkSchedule());
        return "workSchedules/create";
    }

    @GetMapping("/edit/{id}")
    public String editSchedule(@PathVariable int id, Model model) {
        Optional<WorkSchedule> schedule = workScheduleService.getScheduleById(id);
        if (schedule.isPresent()) {
            model.addAttribute("workSchedule", schedule.get());
            return "workSchedules/edit";
        }
        return "redirect:/workSchedules";
    }

    @PostMapping
    public String saveSchedule(@ModelAttribute("workSchedule") WorkSchedule schedule) {
        workScheduleService.saveSchedule(schedule);
        return "redirect:/workSchedules";
    }

    @GetMapping("/delete/{id}")
    public String deleteSchedule(@PathVariable int id) {
        workScheduleService.deleteSchedule(id);
        return "redirect:/workSchedules";
    }

    @GetMapping("/find")
    public String findByAccountID(@RequestParam("accountID") int accountID, Model model) {
        model.addAttribute("workSchedules", workScheduleService.findByAccountID(accountID));
        return "workSchedules/index";
    }
}