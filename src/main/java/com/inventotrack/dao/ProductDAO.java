package com.inventotrack.dao;

import com.inventotrack.model.Product;
import jakarta.persistence.EntityManager;

import java.util.List;

public interface ProductDAO extends BaseDAO<Product> {

    Product findByName(EntityManager em,
                       String name);

    List<Product> findActiveProducts(EntityManager em);

    List<Product> findLowStockProducts(EntityManager em);

    List<Product> search(EntityManager em,
                         String keyword);

}