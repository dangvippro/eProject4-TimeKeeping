package com.timekeeping.timekeeping.models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobID;
    private String title;
    private String description;
    private String requirements;
    private String location;
    private String salaryRange;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date postingDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date closingDate;
    private String status;
    private int experienceYears;
    private String image;

//    @Lob
//    @Column(name = "image")
//    private byte[] image;

    public Job() {
    }

    public Job(int jobID, String title, String description, String requirements, String location, String salaryRange, Date postingDate, Date closingDate, String status, int experienceYears, String image) {
        this.jobID = jobID;
        this.title = title;
        this.description = description;
        this.requirements = requirements;
        this.location = location;
        this.salaryRange = salaryRange;
        this.postingDate = postingDate;
        this.closingDate = closingDate;
        this.status = status;
        this.experienceYears = experienceYears;
        this.image = image;
//        this.image = image;
    }

    

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

//    public byte[] getImage() {
//        return image;
//    }
//
//    public void setImage(byte[] image) {
//        this.image = image;
//    }

   

    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }
    
    
}
