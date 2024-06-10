package com.example.spring_app.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;


//    @Id
//    @ManyToOne
//    @JoinColumn(name = "order_id")
//    private Order order;

    @ManyToOne
    private Product product;

}
