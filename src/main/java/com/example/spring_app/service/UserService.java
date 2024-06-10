package com.example.spring_app.service;

import com.example.spring_app.model.Cart;
import com.example.spring_app.model.Order;
import com.example.spring_app.model.Role;
import com.example.spring_app.model.User;
import com.example.spring_app.repository.RoleRepository;
import com.example.spring_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Transactional
    public String registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return "failure";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCart(new Cart());
        Role userRole = roleRepository.findByName("USER").orElseGet(null);
        if (userRole != null) {
            user.getRoles().add(userRole);
        }
        else {
            Role role = new Role();
            role.setName("USER");
            user.getRoles().add(role);
            roleRepository.save(role);
        }
        userRepository.save(user);
        return "success";
    }

    @Transactional
    public User getCurrentUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        return userRepository.findByUsername(username).orElse(null);
    }

}
