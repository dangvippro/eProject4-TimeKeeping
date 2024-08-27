package com.timekeeping.timekeeping.models;


import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
public class AttendanceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recordID;
    @ManyToOne
    @JoinColumn(name = "accountID", referencedColumnName = "accountID")
    private Account account;
    private Date Date;
    private LocalDateTime clockInTime;
    private LocalDateTime clockOutTime;
    private String status;
    private String notes;
    private double workingHours;
    private int shiftID;

    // Getters and Setters

    public AttendanceRecord() {
    }

    public AttendanceRecord(int recordID, Account account, Date Date, LocalDateTime clockInTime, LocalDateTime clockOutTime, String status, String notes, double workingHours, int shiftID) {
        this.recordID = recordID;
        this.account = account;
        this.Date = Date;
        this.clockInTime = clockInTime;
        this.clockOutTime = clockOutTime;
        this.status = status;
        this.notes = notes;
        this.workingHours = workingHours;
        this.shiftID = shiftID;
    }
    
    

    public int getRecordID() {
        return recordID;
    }

    public void setRecordID(int recordID) {
        this.recordID = recordID;
    }
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public LocalDateTime getClockInTime() {
        return clockInTime;
    }

    public void setClockInTime(LocalDateTime clockInTime) {
        this.clockInTime = clockInTime;
    }

    public LocalDateTime getClockOutTime() {
        return clockOutTime;
    }

    public void setClockOutTime(LocalDateTime clockOutTime) {
        this.clockOutTime = clockOutTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(double workingHours) {
        this.workingHours = workingHours;
    }

    public int getShiftID() {
        return shiftID;
    }

    public void setShiftID(int shiftID) {
        this.shiftID = shiftID;
    }
    public double calculateWorkHours() {
        return Duration.between(clockInTime, clockOutTime).toHours();
    }
}