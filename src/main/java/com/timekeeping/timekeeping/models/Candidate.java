package com.timekeeping.timekeeping.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;

@Entity
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int candidateID;
    private String firstName;
    private String lastName;
    private String email;
    private String description;
    private String requirements;
    private String phoneNumber;
    private String resume;
    private String location;
    private Date candidateDate;// Path to resume file
    private String status; // e.g., Applied, Interviewed, Hired, Rejected

    // Getters and Setters

    public Candidate() {
    }

    public Candidate(int candidateID, String firstName, String lastName, String email, String description, String requirements, String phoneNumber, String resume, String location, Date candidateDate, String status) {
        this.candidateID = candidateID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.description = description;
        this.requirements = requirements;
        this.phoneNumber = phoneNumber;
        this.resume = resume;
        this.location = location;
        this.candidateDate = candidateDate;
        this.status = status;
    }
    
    

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCandidateID() {
        return candidateID;
    }

    public void setCandidateID(int candidateID) {
        this.candidateID = candidateID;
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

    public Date getCandidateDate() {
        return candidateDate;
    }

    public void setCandidateDate(Date candidateDate) {
        this.candidateDate = candidateDate;
    }


}