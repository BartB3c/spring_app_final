package com.example.spring_app.controller;


import com.example.spring_app.model.Cart;
import com.example.spring_app.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/cart/add/{productId}/{quantity}")
    public String addToCart(@PathVariable int productId, @PathVariable int quantity) {
        cartService.addToCart(productId, quantity);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String getCart(Model model) {
        Cart cart = cartService.getCart();
        model.addAttribute("cart", cart);
        return "cart";
    }

    @GetMapping("/cart/remove/{productId}")
    public String removeFromCart(@PathVariable int productId) {
        cartService.removeFromCart(productId);
        return "redirect:/cart";
    }

}
