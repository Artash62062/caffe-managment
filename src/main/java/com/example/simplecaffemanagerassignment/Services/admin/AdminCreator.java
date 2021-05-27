package com.example.simplecaffemanagerassignment.Services.admin;

import com.example.simplecaffemanagerassignment.model.Admin;
import com.example.simplecaffemanagerassignment.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminCreator {
    final
    PasswordEncoder passwordEncoder;
    AdminRepository adminRepository;

    public AdminCreator(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public AdminCreator(PasswordEncoder passwordEncoder, AdminRepository adminRepository) {
        this.passwordEncoder = passwordEncoder;
        this.adminRepository = adminRepository;
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        Admin admin = new Admin();
        admin.setRole("ROLE_ADMIN");
        admin.setName("Admin");
        admin.setLogin("admin");
        String password = "password";
        admin.setPassword(passwordEncoder.encode(password));
        adminRepository.save(admin);
    }
}
