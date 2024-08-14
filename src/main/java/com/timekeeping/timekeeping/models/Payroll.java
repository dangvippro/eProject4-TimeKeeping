package com.timekeeping.timekeeping.models;

import java.util.Date;

public class Payroll {
    private int payrollID;
    private int accountID;
    private int salaryID;
    private Date payDate;
    private double grossSalary;
    private double netSalary;

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
}
