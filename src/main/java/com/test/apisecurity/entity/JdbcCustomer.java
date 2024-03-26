package com.test.apisecurity.entity;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class JdbcCustomer {
    @Id
    private int customerId;
    private String fullName;

    private String email;

    private LocalDate birthdate;
    private String gender;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullNmae) {
        this.fullName = fullNmae;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
