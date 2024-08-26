package com.timekeeping.timekeeping.services;

import com.timekeeping.timekeeping.models.Account;
import com.timekeeping.timekeeping.models.AttendanceRecord;
import com.timekeeping.timekeeping.repositories.AttendanceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRecordRepository attendanceRecordRepository;

    @Autowired
    private PayrollService payrollService;

    @Autowired
    private AccountService accountService;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveAttendanceRecord(AttendanceRecord attendanceRecord) {
        entityManager.persist(attendanceRecord);
    }

    public List<AttendanceRecord> findAttendancesByAccount(Account account) {
        return attendanceRecordRepository.findByAccount(account);
    }

    public void clockIn(int accountId) {
        Account account = accountService.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        AttendanceRecord record = new AttendanceRecord();
        record.setAccount(account);
        record.setDate(new java.util.Date());
        record.setClockInTime(LocalDateTime.now());
        record.setStatus("In");
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
