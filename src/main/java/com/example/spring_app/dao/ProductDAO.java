package com.example.spring_app.dao;


import com.example.spring_app.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductDAO implements IProductDAO {

    @PersistenceContext
    private EntityManager entityManager;
    private final String GET_ALL_JPQL = "FROM com.example.spring_app.model.Product";
    private final String GET_BY_ID_JPQL = "SELECT p from com.example.spring_app.model.Product p WHERE p.id = :id";
    @Override
    public Optional<Product> getById(int id) {
        TypedQuery<Product> query = entityManager.createQuery(GET_BY_ID_JPQL, Product.class);
        query.setParameter("id", id);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Product> getAll() {
        TypedQuery<Product> query = entityManager.createQuery(GET_ALL_JPQL, Product.class);
        return query.getResultList();
    }

    @Override
    public void saveOrUpdate(Product book) {
        System.out.println("PRODUCT"+ book);
        if (getById(book.getId()).isEmpty()){
            entityManager.persist(book);
        } else {
            entityManager.merge(book);
        }
    }

    @Override
    public void delete(int id) {
        Product product = getById(id).orElse(null);
        if (product != null){
            entityManager.remove(product);
        }
    }
}
