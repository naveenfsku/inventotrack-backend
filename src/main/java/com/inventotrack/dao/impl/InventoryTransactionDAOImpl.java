package com.inventotrack.dao.impl;

import com.inventotrack.dao.InventoryTransactionDAO;
import com.inventotrack.enums.InventoryTransactionType;
import com.inventotrack.model.InventoryTransaction;

import jakarta.persistence.EntityManager;

import java.util.List;

public class InventoryTransactionDAOImpl
        implements InventoryTransactionDAO {

    @Override
    public InventoryTransaction save(EntityManager em,
                                     InventoryTransaction transaction) {

        em.persist(transaction);

        return transaction;
    }

    @Override
    public InventoryTransaction update(EntityManager em,
                                       InventoryTransaction transaction) {

        return em.merge(transaction);
    }

    @Override
    public void delete(EntityManager em,
                       Long id) {

        InventoryTransaction transaction =
                em.find(InventoryTransaction.class, id);

        if (transaction != null) {
            em.remove(transaction);
        }

    }

    @Override
    public InventoryTransaction findById(EntityManager em,
                                         Long id) {

        return em.find(
                InventoryTransaction.class,
                id);

    }

    @Override
    public List<InventoryTransaction> findAll(EntityManager em) {

        return em.createQuery(
                        "SELECT t FROM InventoryTransaction t ORDER BY t.transactionDate DESC ",
                        InventoryTransaction.class)
                .getResultList();

    }

    @Override
    public List<InventoryTransaction> findByProduct(
            EntityManager em,
            Long productId) {

        return em.createQuery(
                        "SELECT t FROM InventoryTransaction t WHERE t.product.id=:productid ORDER BY t.transactionDate DESC",
                        InventoryTransaction.class)
                .setParameter("productid", productId)
                .getResultList();

    }

    @Override
    public List<InventoryTransaction> findByTransactionType(
            EntityManager em,
            InventoryTransactionType type) {

        return em.createQuery(
                        "SELECT t FROM InventoryTransaction t WHERE t.transactionType=:type ORDER BY t.transactionDate DESC",
                        InventoryTransaction.class)
                .setParameter("type", type)
                .getResultList();

    }

    @Override
    public List<InventoryTransaction> findByReference(
            EntityManager em,
            String referenceType,
            Long referenceId) {

        return em.createQuery(
                        "SELECT t FROM InventoryTransaction t WHERE t.referenceType=:referenceType AND t.referenceId=:referenceId ORDER BY t.transactionDate DESC",

                        InventoryTransaction.class)
                .setParameter("referenceType", referenceType)
                .setParameter("referenceId", referenceId)
                .getResultList();

    }

}