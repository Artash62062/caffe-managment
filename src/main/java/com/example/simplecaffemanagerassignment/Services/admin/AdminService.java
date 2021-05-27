package com.example.simplecaffemanagerassignment.Services.admin;

import com.example.simplecaffemanagerassignment.model.Admin;
import com.example.simplecaffemanagerassignment.model.Manager;
import com.example.simplecaffemanagerassignment.model.User;

public interface AdminService {
    Admin getOneByLogin();
    User createNewUser(User user);
}
