package com.inventotrack.dao.impl;

import com.inventotrack.dao.ProductDAO;
import com.inventotrack.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.List;

public class ProductDAOImpl extends AbstractDAOImpl<Product>
        implements ProductDAO {

    public ProductDAOImpl() {
        super(Product.class);
    }

    @Override
    public Product findByName(EntityManager em, String name) {

        try {

            return em.createQuery(
                            "SELECT p FROM Product p " +
                                    "WHERE p.name = :name " +
                                    "AND p.active = true",
                            Product.class)
                    .setParameter("name", name)
                    .getSingleResult();

        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Product> findActiveProducts(EntityManager em) {

        return em.createQuery(
                        "SELECT p FROM Product p " +
                                "WHERE p.active = true " +
                                "ORDER BY p.name",
                        Product.class)
                .getResultList();
    }

    @Override
    public List<Product> findLowStockProducts(EntityManager em) {

        return em.createQuery(
                        "SELECT p FROM Product p " +
                                "WHERE p.active = true " +
                                "AND p.stockQuantity <= p.lowStockThreshold " +
                                "ORDER BY p.stockQuantity",
                        Product.class)
                .getResultList();
    }

    @Override
    public List<Product> search(EntityManager em, String keyword) {

        return em.createQuery(
                        "SELECT p FROM Product p " +
                                "WHERE p.active = true " +
                                "AND (LOWER(p.name) LIKE LOWER(:keyword) " +
                                "OR LOWER(p.category) LIKE LOWER(:keyword)) " +
                                "ORDER BY p.name",
                        Product.class)
                .setParameter("keyword", "%" + keyword + "%")
                .getResultList();
    }
}