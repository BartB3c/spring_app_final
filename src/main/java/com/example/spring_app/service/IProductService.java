package com.example.spring_app.service;

import com.example.spring_app.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    void saveOrUpdate(Product book);
    Optional<Product> getById(int id);
    List<Product> getAll();
    void delete(int id);
}
