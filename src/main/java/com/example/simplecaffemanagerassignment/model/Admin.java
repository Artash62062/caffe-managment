package com.example.simplecaffemanagerassignment.model;

import javax.persistence.Entity;

@Entity (name = "Admins")
public class Admin extends User {
    @Override
    public void setRole(String role) {
        super.setRole("ROLE_ADMIN");
    }
}
