package com.example.spring_app.dao;

import com.example.spring_app.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductDAO {
    Optional<Product> getById(int id);
    List<Product> getAll();
    void saveOrUpdate(Product book);
    void delete(int id);
}
