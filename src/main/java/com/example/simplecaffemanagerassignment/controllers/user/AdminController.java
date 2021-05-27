package com.example.simplecaffemanagerassignment.controllers.user;

import com.example.simplecaffemanagerassignment.Services.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping
    public String adminPage(Model model) {
        return "adminPage";
    }
    @GetMapping("/register")
    public String registerUser(Model model){
        return "redirect:/register";
    }

}
