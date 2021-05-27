package com.example.simplecaffemanagerassignment.controllers;

import com.example.simplecaffemanagerassignment.Services.user.UserService;
import com.example.simplecaffemanagerassignment.model.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/register")
public class RegisterController {
     private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String register(Model model) {
        return "/register";
    }

    @PostMapping
    public String addUser(User user, Model model) {
        User userFromDb = userService.getOneByLogin(user.getLogin());
        int userExistCode;
        if (userFromDb != null) {
            userExistCode = 1;
            model.addAttribute("UserExistCode", userExistCode);
            return "/register";
        }
        userService.createNewUser(user);
        return "redirect:/";
    }
}
