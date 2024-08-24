package com.timekeeping.timekeeping.models;

import jakarta.persistence.*;

@Entity
public class Bonus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bonusID;
    private String bonusType;
    private int amount;

    @ManyToOne
    @JoinColumn(name = "payrollID")
    private Payroll payroll;

    public Bonus() {
    }

    public Bonus(int bonusID, String bonusType, int amount, Payroll payroll) {
        this.bonusID = bonusID;
        this.bonusType = bonusType;
        this.amount = amount;
        this.payroll = payroll;
    }

    // Getters and Setters

    public int getBonusID() {
        return bonusID;
    }

    public void setBonusID(int bonusID) {
        this.bonusID = bonusID;
    }

    public String getBonusType() {
        return bonusType;
    }

    public void setBonusType(String bonusType) {
        this.bonusType = bonusType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Payroll getPayroll() {
        return payroll;
    }

    public void setPayroll(Payroll payroll) {
        this.payroll = payroll;
    }

    // Other methods...
}
