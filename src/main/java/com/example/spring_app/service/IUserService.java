package com.example.spring_app.service;

import com.example.spring_app.model.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> findByUsername(String username);
    String registerUser(User user);
}
