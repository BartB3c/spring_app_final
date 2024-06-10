package com.example.spring_app.controller;

import com.example.spring_app.model.User;
import com.example.spring_app.service.ProductService;
import com.example.spring_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {
    @Autowired
    ProductService productService;
    @Autowired
    private UserService userService ;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        String result = userService.registerUser(user);
        model.addAttribute("message", result);
        if (result.equals("success")) {
            return "redirect:/login";
        }
        return "register";
    }

}