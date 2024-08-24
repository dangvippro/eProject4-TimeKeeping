package com.timekeeping.timekeeping.models;

import java.util.Date;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class Payroll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payrollID;
    private int accountID;
    private int salaryID;
    private Date payDate;
    private double grossSalary;
    private double netSalary;

    @OneToMany(mappedBy = "payroll", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Deduction> deductions;

    @OneToMany(mappedBy = "payroll", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Bonus> bonuses;

    public Payroll() {
    }

    public Payroll(int payrollID, int accountID, int salaryID, Date payDate, double grossSalary, double netSalary) {
        this.payrollID = payrollID;
        this.accountID = accountID;
        this.salaryID = salaryID;
        this.payDate = payDate;
        this.grossSalary = grossSalary;
        this.netSalary = netSalary;
    }

    // Getters and Setters

    public int getPayrollID() {
        return payrollID;
    }

    public void setPayrollID(int payrollID) {
        this.payrollID = payrollID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getSalaryID() {
        return salaryID;
    }

    public void setSalaryID(int salaryID) {
        this.salaryID = salaryID;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public double getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(double netSalary) {
        this.netSalary = netSalary;
    }

    public List<Deduction> getDeductions() {
        return deductions;
    }

    public void setDeductions(List<Deduction> deductions) {
        this.deductions = deductions;
    }

    public List<Bonus> getBonuses() {
        return bonuses;
    }

    public void setBonuses(List<Bonus> bonuses) {
        this.bonuses = bonuses;
    }

    // Method to calculate net salary
    public void calculateNetSalary() {
        double totalDeductions = deductions.stream().mapToDouble(Deduction::getAmount).sum();
        double totalBonuses = bonuses.stream().mapToDouble(Bonus::getAmount).sum();
        this.netSalary = this.grossSalary - totalDeductions + totalBonuses;
    }

    // Other methods...
}
