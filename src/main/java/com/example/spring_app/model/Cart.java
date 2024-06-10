package com.example.spring_app.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    public List<CartItem> getItems() {
        return items;
    }

    @Getter
    @OneToOne(mappedBy = "cart")
    private User user;

    public void addItem(Product product, int quantity) {
        for (CartItem item : this.items) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        this.items.add(new CartItem(this, product, quantity));
    }
    public void removeItem(Product product) {
        for (CartItem item : this.items) {
            if (item.getProduct().getId() == product.getId()) {
                int newQuantity = item.getQuantity() - 1;
                if (newQuantity > 0) {
                    item.setQuantity(newQuantity);
                } else {
                    this.items.remove(item);
                }
                return;
            }
        }
    }

}
