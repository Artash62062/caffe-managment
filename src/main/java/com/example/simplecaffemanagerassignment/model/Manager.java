package com.example.simplecaffemanagerassignment.model;

import org.springframework.context.annotation.Primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;

@Entity(name = "managers")
public class Manager extends User {
    @Column(unique = true)
    private String managerCode;

    @Override
    public void setRole(String role) {
        super.setRole("ROLE_MANAGER");
    }

    public String getManagerCode() {
        return managerCode;
    }

    public void setManagerCode(String managerCode) {
        this.managerCode = managerCode;
    }
}
