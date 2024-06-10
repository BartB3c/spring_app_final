package com.example.spring_app.service;

import com.example.spring_app.dao.IProductDAO;
import com.example.spring_app.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    private final IProductDAO productDAO;

    public ProductService(IProductDAO bookDAO) {
        this.productDAO = bookDAO;
    }

    @Override
    @Transactional
    public Optional<Product> getById(int id) {
        return this.productDAO.getById(id);
    }

    @Override
    @Transactional
    public List<Product> getAll() {
        return this.productDAO.getAll();
    }

    @Transactional
    public void saveOrUpdate(Product product) {
        this.productDAO.saveOrUpdate(product);
    }

    @Override
    @Transactional
    public void delete(int id) {
       productDAO.delete(id);
    }
}
