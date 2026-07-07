package com.inventotrack.service;

import com.inventotrack.dto.InventoryTransactionDTO;
import com.inventotrack.enums.InventoryTransactionType;
import com.inventotrack.model.Product;
import jakarta.persistence.EntityManager;

import java.util.List;

public interface InventoryTransactionService {

    InventoryTransactionDTO createTransaction(
            InventoryTransactionDTO dto);

    InventoryTransactionDTO getTransactionById(Long id);

    List<InventoryTransactionDTO> getAllTransactions();

    List<InventoryTransactionDTO> getTransactionsByProduct(
            Long productId);

    List<InventoryTransactionDTO> getTransactionsByType(
            InventoryTransactionType type);

    List<InventoryTransactionDTO> getTransactionsByReference(
            String referenceType,
            Long referenceId);
    void recordTransaction(
            EntityManager em,
            Product product,
            InventoryTransactionType type,
            Integer quantity,
            Integer previousStock,
            Integer currentStock,
            String referenceType,
            Long referenceId,
            String remarks);
}