package com.inventotrack.dao.impl;

import com.inventotrack.dao.BaseDAO;
import jakarta.persistence.EntityManager;

import java.util.List;

public abstract class AbstractDAOImpl<T> implements BaseDAO<T> {

    private final Class<T> entityClass;

    protected AbstractDAOImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T save(EntityManager em, T entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public T update(EntityManager em, T entity) {
        return em.merge(entity);
    }

    @Override
    public void delete(EntityManager em, Long id) {

        T entity = em.find(entityClass, id);

        if (entity != null) {
            em.remove(entity);
        }
    }

    @Override
    public T findById(EntityManager em, Long id) {
        return em.find(entityClass, id);
    }

    @Override
    public List<T> findAll(EntityManager em) {

        return em.createQuery(
                "FROM " + entityClass.getSimpleName(),
                entityClass
        ).getResultList();

    }

    protected Class<T> getEntityClass() {
        return entityClass;
    }
}