package com.inventotrack.dao;

import com.inventotrack.enums.InventoryTransactionType;
import com.inventotrack.model.InventoryTransaction;

import jakarta.persistence.EntityManager;

import java.util.List;

public interface InventoryTransactionDAO {

    InventoryTransaction save(EntityManager em,
                              InventoryTransaction transaction);

    InventoryTransaction update(EntityManager em,
                                InventoryTransaction transaction);

    void delete(EntityManager em,
                Long id);

    InventoryTransaction findById(EntityManager em,
                                  Long id);

    List<InventoryTransaction> findAll(EntityManager em);

    List<InventoryTransaction> findByProduct(EntityManager em,
                                             Long productId);

    List<InventoryTransaction> findByTransactionType(
            EntityManager em,
            InventoryTransactionType type);

    List<InventoryTransaction> findByReference(
            EntityManager em,
            String referenceType,
            Long referenceId);

}