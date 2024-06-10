package com.example.spring_app.service;

import com.example.spring_app.dao.IProductDAO;
import com.example.spring_app.model.Product;
import com.example.spring_app.model.Cart;
import com.example.spring_app.model.User;
import com.example.spring_app.repository.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private IProductDAO productRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public Cart getCart(){
        User user = userService.getCurrentUser();
        return user.getCart();
    }
    @Transactional
    public Cart addToCart(int productId, int quantity){
        Cart cart = getCart();
        Product product = productRepository.getById(productId).orElseThrow(()
                -> new RuntimeException("Book not found"));
        cart.addItem(product, quantity);
        return saveCart(cart);
    }

    @Transactional
    public Cart removeFromCart(int bookId){
        Cart cart = this.getCart();
        Product product = productRepository.getById(bookId).orElseThrow(()
                -> new RuntimeException("Book not found"));
        cart.removeItem(product);
        return saveCart(cart);
    }

    @Transactional
    public Cart saveCart(Cart cart){
        return cartRepository.save(cart);
    }


}
