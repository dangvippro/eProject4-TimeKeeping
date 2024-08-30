package com.timekeeping.timekeeping.controllers;
import com.timekeeping.timekeeping.models.AttendanceRecord;
import com.timekeeping.timekeeping.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AttendanceRecordController {

//    @Autowired
//    private AttendanceService attendanceService;
//
//    @GetMapping("/attendanceSuccess")
//    public String attendanceSuccess(@RequestParam("name") String name,
//                                    @RequestParam("age") int age,
//                                    Model model) {
//        // Save attendance
//        AttendanceRecord attendance = attendanceService.saveAttendance(name, age);
//
//        // Set model attributes
//        model.addAttribute("name", name);
//        model.addAttribute("age", age);
//        model.addAttribute("shiftID", attendance.getShiftID());
//
//        // Assuming Date is a LocalDate object
//        LocalDate attendanceDate = attendance.getDate();
//        model.addAttribute("date", attendanceDate);
//
//        // Assuming clockInTime and clockOutTime are LocalDateTime objects
//        model.addAttribute("clockInTime", attendance.getClockInTime().toLocalTime());
//        model.addAttribute("clockOutTime", attendance.getClockOutTime().toLocalTime());
//
//        return "home/attendance-success";
//    }
}
