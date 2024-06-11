package com.example.spring_app.dao;

import com.example.spring_app.model.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDAO {
    @PersistenceContext
    private EntityManager entityManager;
    private final String GET_ALL_JPQL = "FROM com.example.spring_app.model.Order";
//    private final String GET_BY_ID_JPQL = "SELECT o from com.example.spring_app.model.Order o WHERE o.id = :id";

    public List<Order> getAll() {
        TypedQuery<Order> query = entityManager.createQuery(GET_ALL_JPQL, Order.class);
        return query.getResultList();
    }
//    public Optional<Order> getById(int id) {
//        TypedQuery<Order> query = entityManager.createQuery(GET_BY_ID_JPQL, Order.class);
//        query.setParameter("id", id);
//        try {
//            return Optional.of(query.getSingleResult());
//        } catch (NoResultException e) {
//            return Optional.empty();
//        }
//    }
}
