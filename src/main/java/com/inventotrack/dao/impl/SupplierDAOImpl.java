package com.inventotrack.dao.impl;

import com.inventotrack.dao.SupplierDAO;
import com.inventotrack.model.Supplier;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.List;

public class SupplierDAOImpl
        extends AbstractDAOImpl<Supplier>
        implements SupplierDAO {

    public SupplierDAOImpl() {
        super(Supplier.class);
    }

    @Override
    public Supplier findByName(EntityManager em,
                               String name) {

        try {

            return em.createQuery(
                            "SELECT s FROM Supplier s WHERE s.name = :name",
                            Supplier.class)
                    .setParameter("name", name)
                    .getSingleResult();

        } catch (NoResultException e) {

            return null;

        }

    }

    @Override
    public List<Supplier> findActiveSuppliers(EntityManager em) {

        return em.createQuery(
                        "SELECT s FROM Supplier s WHERE s.active = true ORDER BY s.name",
                        Supplier.class)
                .getResultList();

    }

    @Override
    public List<Supplier> search(EntityManager em,
                                 String keyword) {

        return em.createQuery(
                        "SELECT s FROM Supplier s " +
                                "WHERE LOWER(s.name) LIKE LOWER(:keyword) " +
                                "OR LOWER(s.contactPerson) LIKE LOWER(:keyword)",
                        Supplier.class)
                .setParameter("keyword", "%" + keyword + "%")
                .getResultList();

    }

}