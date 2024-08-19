package com.timekeeping.timekeeping.models;

import jakarta.persistence.*;

import java.util.Date;
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountID;
    private int roleId;
    private String status;
    private String address;
    private Date hireDate;
    private int departmentID;
    private String position;
    private String phoneNumber;
    private String email;
    private Date birthDate;
    private String gender;
    private String fullName;
    private int codeBank;
    private String password;
    private String username;
    @ManyToOne
    private Role role;

    public Account() {
    }

    public Account(int accountID, int roleId, String status, String address, Date hireDate, int departmentID,
                   String position, String phoneNumber, String email, Date birthDate, String gender,
                   String fullName, int codeBank, String password, String username, Role role) {
        this.accountID = accountID;
        this.roleId = roleId;
        this.status = status;
        this.address = address;
        this.hireDate = hireDate;
        this.departmentID = departmentID;
        this.position = position;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
        this.fullName = fullName;
        this.codeBank = codeBank;
        this.password = password;
        this.username = username;
        this.role = role;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getCodeBank() {
        return codeBank;
    }

    public void setCodeBank(int codeBank) {
        this.codeBank = codeBank;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
