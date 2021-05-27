package com.example.simplecaffemanagerassignment.Services.user;

import com.example.simplecaffemanagerassignment.Services.manager.ManagerService;
import com.example.simplecaffemanagerassignment.Services.waiter.WaiterService;
import com.example.simplecaffemanagerassignment.model.Manager;
import com.example.simplecaffemanagerassignment.model.User;
import com.example.simplecaffemanagerassignment.model.Waiter;
import com.example.simplecaffemanagerassignment.repositories.AdminRepository;
import com.example.simplecaffemanagerassignment.repositories.ManagerRepository;
import com.example.simplecaffemanagerassignment.repositories.WaiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    final PasswordEncoder passwordEncoder;
    final ManagerRepository managerRepository;
    final WaiterRepository waiterRepository;
    final ManagerService managerService;
    final WaiterService waiterService;
    final AdminRepository adminRepository;

    public UserServiceImpl(ManagerService managerService, WaiterService waiterService, PasswordEncoder passwordEncoder, ManagerRepository managerRepository, WaiterRepository waiterRepository, AdminRepository adminRepository) {
        this.passwordEncoder = passwordEncoder;
        this.managerRepository = managerRepository;
        this.waiterRepository = waiterRepository;
        this.managerService = managerService;
        this.waiterService = waiterService;
        this.adminRepository = adminRepository;
    }


    @Override
    public User createNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole().equals("ROLE_MANAGER")) {
            return managerService.createNewManager(user);
        } else if (user.getRole().equals("ROLE_WAITER")) {
            return waiterService.createNewWaiter(user);
        }
        return user;
    }

    @Override
    public User getOneByLogin(String login) {
        User user = managerService.getOneByLogin(login);
        if (user == null) {
            user = waiterService.getOneByLogin(login);
        }
        if (user == null) {
            user = adminRepository.getByLogin(login);
        }
        return user;
    }

}
