package com.inventotrack.dao;

import jakarta.persistence.EntityManager;

import java.util.List;

public interface BaseDAO<T> {

    T save(EntityManager em, T entity);

    T update(EntityManager em, T entity);

    void delete(EntityManager em, Long id);

    T findById(EntityManager em, Long id);

    List<T> findAll(EntityManager em);
}