package com.timekeeping.timekeeping.controllers;

import com.timekeeping.timekeeping.models.Notification;
import com.timekeeping.timekeeping.models.Recruitment;
import com.timekeeping.timekeeping.services.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/recruitments")
public class RecruitmentController {
    @Autowired
    private RecruitmentService recruitmentService;



    @GetMapping("/list")
    public String listRecruitments(Model model) {
        List<Recruitment> recruitments = recruitmentService.findAll();
        model.addAttribute("recruitments", recruitments);
        return "recruitments/recruitment-list";
    }

    @GetMapping("/new")
    public String showRecruitmentForm(Model model) {
        model.addAttribute("recruitments", new Recruitment());
        return "recruitments/recruitment-schedule";
    }




@PostMapping("/schedule")
public String scheduleRecruitment(@ModelAttribute Recruitment recruitment, Model model) {
    System.out.println("Received recruitment: " + recruitment);
        // Save the recruitment information to the database
    recruitmentService.save(recruitment);

    // Redirect to the recruitment list page to display the updated information
    return "redirect:/recruitments/list";
}


    private void sendNotification(Recruitment recruitment) {
        Notification notification = new Notification();
        notification.setRecruitmentId(recruitment.getRecruitmentId());
        notification.setAccountId(recruitment.getAccountId()); // Assuming you have an account ID field in recruitment
        notification.setResult("Scheduled"); // Example result status
        notification.setNotificationDate(LocalDateTime.now());
//        notification.setComments("Interview scheduled for " + recruitment.getScheduledDate()); // Example comment

        // Save the notification to the database
//        NotificationService.save(notification);
    }


//    @PostMapping("/update")
//    public Recruitment update(int recruitmentId, Recruitment updatedRecruitment) {
//        Optional<Recruitment> existingRecruitment = recruitmentRepository.findById(recruitmentId);
//        if (existingRecruitment.isPresent()) {
//            Recruitment recruitment = existingRecruitment.get();
//            // Update fields...
//            return recruitmentRepository.save(recruitment);
//        } else {
//            throw new RecruitmentNotFoundException("Recruitment not found with id: " + recruitmentId);
//        }
//    }
//}





    @GetMapping("/edit/{recruitmentId}")
    public String getRecruitment(@PathVariable int recruitmentId, Model model) {
        Optional<Recruitment> recruitment = recruitmentService.findById(recruitmentId);
        model.addAttribute("recruitment", recruitment);
        return "recruitments/recruitment-edit";
    }




    @GetMapping("/delete/{recruitmentId}")
    public String deleteRecruitment(@PathVariable int recruitmentId) {
        recruitmentService.deleteById(recruitmentId);
        return "redirect:/recruitments/list";
    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "test"; // Ensure there is a "test.html" template
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}