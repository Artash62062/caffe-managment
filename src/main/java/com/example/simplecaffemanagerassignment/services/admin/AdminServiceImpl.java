package com.example.simplecaffemanagerassignment.Services.admin;

import com.example.simplecaffemanagerassignment.Services.admin.AdminService;
import com.example.simplecaffemanagerassignment.Services.manager.ManagerService;
import com.example.simplecaffemanagerassignment.Services.user.UserService;
import com.example.simplecaffemanagerassignment.model.Admin;
import com.example.simplecaffemanagerassignment.model.Manager;
import com.example.simplecaffemanagerassignment.model.User;
import com.example.simplecaffemanagerassignment.repositories.AdminRepository;
import com.example.simplecaffemanagerassignment.repositories.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final UserService userService;

    public AdminServiceImpl(AdminRepository adminRepository, PasswordEncoder passwordEncoder,UserService userService) {
        this.adminRepository = adminRepository;
        this.userService = userService;
    }


    @Override
    public Admin getOneByLogin() {
        String login = "admin";
        return adminRepository.getByLogin(login);
    }

    @Override
    public User createNewUser (User user) {
        return userService.createNewUser(user);
    }
}
