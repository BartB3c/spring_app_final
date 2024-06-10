package com.example.spring_app.controller;

import com.example.spring_app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    ProductService productService;

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        @RequestParam(value = "message", required = false) String message,
                        Model model) {
        if ( message!=null){
            model.addAttribute("message",message);
        }
        if (error != null) {
            model.addAttribute("message", "Nieprawidłowa nazwa użytkownika lub hasło!");
        }
        if (logout != null) {
            model.addAttribute("message", "Pomyślnie wylogowano!");
        }
        return "login";
    }
    @RequestMapping(path = {"/product-list"}, method = RequestMethod.GET)
    public String productList(Model model){
        model.addAttribute("products", this.productService.getAll());
        return "product-list";
    }
}