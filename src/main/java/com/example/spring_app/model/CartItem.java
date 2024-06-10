package com.example.spring_app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    private Product product;
    private int quantity;

    public CartItem() {

    }

    public CartItem(Cart cart, Product book, int quantity) {
        this.cart = cart;
        this.product = book;
        this.quantity = quantity;
    }

}
