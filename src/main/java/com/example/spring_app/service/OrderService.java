package com.example.spring_app.service;

import com.example.spring_app.dao.OrderDAO;
import com.example.spring_app.model.*;
import com.example.spring_app.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;

    @Autowired
    OrderDAO orderDAO;


    @Transactional
    public Order submitOrder(){
        User user = userService.getCurrentUser();
        Cart cart = user.getCart();

        Order order = new Order();
        order.setDate(new Date());
        order.setStatus(OrderStatus.SUBMITTED);
        order.setUser(user);
        for (CartItem cartItem : cart.getItems()){
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            order.getItems().add(orderItem);
        }
        cart.getItems().clear();
        cartService.saveCart(cart);
        return orderRepository.save(order);
    }

    @Transactional
    public Order getOrder(Long orderId){
        return orderRepository.findById(orderId).orElseThrow(() ->
                new RuntimeException("Order not found"));
    }

    @Transactional
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public List<Order> getAll(){
        return this.orderDAO.getAll();
    }

}
