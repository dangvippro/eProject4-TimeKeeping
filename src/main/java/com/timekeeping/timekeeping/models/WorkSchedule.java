package com.timekeeping.timekeeping.models;

import com.timekeeping.timekeeping.enums.ApprovalStatus;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@NamedQuery(name = "WorkSchedule.findByAccountID", query = "SELECT w FROM WorkSchedule w WHERE w.account.accountID = :accountID")
public class WorkSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scheduleId;

    @ManyToOne
    @JoinColumn(name = "shiftId")
    private Shift shift;

    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate requestDate;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "NVARCHAR(255)")
    private ApprovalStatus status;

    // Constructors
    public WorkSchedule() {}

    public WorkSchedule(int scheduleId, Shift shift, Account account, LocalDate date, LocalDate requestDate, ApprovalStatus status) {
        this.scheduleId = scheduleId;
        this.shift = shift;
        this.account = account;
        this.date = date;
        this.requestDate = requestDate;
        this.status = status;
    }

    // Getters and Setters
    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public ApprovalStatus getStatus() {
        return status;
    }

    public void setStatus(ApprovalStatus status) {
        this.status = status;
    }
}
