package com.example.simplecaffemanagerassignment.Services.user;

import com.example.simplecaffemanagerassignment.model.User;

public interface UserService {
    User createNewUser(User user);
    User getOneByLogin(String login);
}
