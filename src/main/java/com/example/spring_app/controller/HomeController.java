package com.example.spring_app.controller;

import com.example.spring_app.service.ProductService;
import com.example.spring_app.service.CartService;
import com.example.spring_app.service.OrderService;
import com.example.spring_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class HomeController {

    @Autowired
    ProductService productService;
    @Autowired
    CartService cartService;

    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;

    @GetMapping({"/home","/"})
    public String home() {
        return "home";
    }

    @GetMapping("/admin/adminpanel")
    public String adminpanel() {
        return "adminpanel";
    }
    @RequestMapping(path = {"/main","/",}, method = RequestMethod.GET)
    public String main(Model model){
        model.addAttribute("products", this.productService.getAll());
        return "main";
    }

    @RequestMapping(path = {"/available_products"}, method = RequestMethod.GET)
    public String userProductList(Model model){
        model.addAttribute("products", this.productService.getAll());
        return "available_products";
    }

    @RequestMapping(path = {"/all_orders"}, method = RequestMethod.GET)
    public String allOrderList(Model model){
        model.addAttribute("orders", this.orderService.getAll());
        return "all_orders";
    }
    @RequestMapping(path = {"/my_orders"}, method = RequestMethod.GET)
    public String userOrderList(Model model){
        model.addAttribute("orders", this.userService.getCurrentUser().getOrders());
        return "my_orders";
    }



}
