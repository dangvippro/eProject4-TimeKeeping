package com.timekeeping.timekeeping.models;

import com.timekeeping.timekeeping.enums.ActivityStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@NamedQuery(name = "Activity.findByActivityName", query = "SELECT a FROM Activity a WHERE a.activityName LIKE :activityName")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int activityId;

    @Column(columnDefinition = "NVARCHAR(255)")
    private String activityName;
    @Column(columnDefinition = "NTEXT")
    private String description;
    private LocalDate date;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String location;
    private double budget;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "NVARCHAR(255)")
    private ActivityStatus status;

    // Constructors
    public Activity() {}

    public Activity(int activityId, String activityName, String description, LocalDate date, String location, double budget, ActivityStatus status) {
        this.activityId = activityId;
        this.activityName = activityName;
        this.description = description;
        this.date = date;
        this.location = location;
        this.budget = budget;
        this.status = status;
    }

    // Getters and Setters
    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public ActivityStatus getStatus() {
        return status;
    }

    public void setStatus(ActivityStatus status) {
        this.status = status;
    }
}
