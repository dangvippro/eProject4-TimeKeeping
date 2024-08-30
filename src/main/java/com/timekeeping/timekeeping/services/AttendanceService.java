/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.timekeeping.timekeeping.services;

import com.timekeeping.timekeeping.models.AttendanceRecord;
import com.timekeeping.timekeeping.repositories.AttendanceRecordRepository;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class AttendanceService {

    @Autowired
    private AttendanceRecordRepository attendanceRecordRepository;

    public void clockIn(int accountId) {
        AttendanceRecord record = new AttendanceRecord();
        record.setAccountID(accountId);
        record.setDate(new java.util.Date()); // Set the current date
        record.setClockInTime(LocalDateTime.now()); // Set the current time as clock-in time
        record.setStatus("In"); // Status could be "In" or other values as needed
        attendanceRecordRepository.save(record);
    }
    
     public void clockOut(int recordID) {
        AttendanceRecord record = attendanceRecordRepository.findById(recordID)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        record.setClockOutTime(LocalDateTime.now());

        // Calculate and set working hours
        if (record.getClockInTime() != null && record.getClockOutTime() != null) {
            record.setWorkingHours(calculateWorkingHours(record.getClockInTime(), record.getClockOutTime()));
        } else {
            record.setWorkingHours(0); // Handle cases where clock-in or clock-out time is missing
        }

        record.setStatus("Out");
        attendanceRecordRepository.save(record);
    }

//    public AttendanceRecord saveAttendance(String name, int age) {
//        AttendanceRecord attendance = new AttendanceRecord();
//        attendance.setAccountID(name);
//        attendance.setAccountID(age);
//        attendance.setClockInTime(LocalDateTime.now());
//        attendance.setClockOutTime(LocalDateTime.now());
//
//        return AttendanceRecordRepository.save(attendance);
//    }
//
    

    private double calculateWorkingHours(LocalDateTime clockInTime, LocalDateTime clockOutTime) {
        if (clockInTime == null || clockOutTime == null) {
            return 0; // Handle the case where one or both times are null
        }

        Duration duration = Duration.between(clockInTime, clockOutTime);
        return duration.toMinutes() / 60.0; // Convert minutes to hours
    }

    public Optional<AttendanceRecord> getAttendanceRecordById(int recordID) {
        return attendanceRecordRepository.findById(recordID);
    }

    public Iterable<AttendanceRecord> getAllAttendanceRecords() {
        return attendanceRecordRepository.findAll();
    }
}