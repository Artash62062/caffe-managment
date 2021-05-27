package com.example.simplecaffemanagerassignment.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.UniqueConstraint;

@MappedSuperclass
public  class User extends AbstractBaseEntity {
    private String name;
    @Column (unique = true)
    private String login;
    private String password;
    private String role;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
