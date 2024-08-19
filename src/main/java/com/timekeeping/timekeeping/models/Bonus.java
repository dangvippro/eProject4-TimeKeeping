package com.timekeeping.timekeeping.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Bonus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bonusID;
    private String bonusType;
    private int amount;

    public Bonus() {
    }

    public Bonus(int bonusID, String bonusType, int amount) {
        this.bonusID = bonusID;
        this.bonusType = bonusType;
        this.amount = amount;
    }

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

    // Method to apply the bonus to the gross salary
    public double applyBonus(double grossSalary) {
        return grossSalary + this.amount;
    }

    // Optionally, you can add other methods like toString, equals, hashCode, etc.
}
