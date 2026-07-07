package com.inventotrack.service.impl;

import com.inventotrack.constants.AppConstants;
import com.inventotrack.dao.InventoryTransactionDAO;
import com.inventotrack.dao.ProductDAO;
import com.inventotrack.enums.InventoryTransactionType;
import com.inventotrack.factory.DAOFactory;
import com.inventotrack.dto.InventoryTransactionDTO;
import com.inventotrack.exception.ResourceNotFoundException;
import com.inventotrack.mapper.InventoryTransactionMapper;
import com.inventotrack.model.InventoryTransaction;
import com.inventotrack.model.Product;
import com.inventotrack.service.InventoryTransactionService;
import com.inventotrack.util.JPAUtil;

import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryTransactionServiceImpl
        implements InventoryTransactionService {

    private final InventoryTransactionDAO inventoryDAO =
            DAOFactory.getInventoryTransactionDAO();

    private final ProductDAO productDAO =
            DAOFactory.getProductDAO();

    @Override
    public InventoryTransactionDTO createTransaction(
            InventoryTransactionDTO dto) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            Product product =
                    productDAO.findById(em, dto.getProductId());

            if (product == null) {
                throw new ResourceNotFoundException(
                        "Product not found.");
            }

            InventoryTransaction transaction =
                    InventoryTransactionMapper.toEntity(dto);

            transaction.setProduct(product);

            inventoryDAO.save(em, transaction);

            em.getTransaction().commit();

            return InventoryTransactionMapper.toDTO(transaction);

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw e;

        } finally {

            em.close();

        }

    }

    @Override
    public void recordTransaction(
            EntityManager em,
            Product product,
            InventoryTransactionType type,
            Integer quantity,
            Integer previousStock,
            Integer currentStock,
            String referenceType,
            Long referenceId,
            String remarks) {

        InventoryTransaction transaction = new InventoryTransaction();

        transaction.setProduct(product);
        transaction.setTransactionType(type);
        transaction.setQuantity(quantity);
        transaction.setPreviousStock(previousStock);
        transaction.setCurrentStock(currentStock);
        transaction.setReferenceType(referenceType);
        transaction.setReferenceId(referenceId);
        transaction.setRemarks(remarks);
        transaction.setTransactionDate(LocalDateTime.now());

        transaction.setCreatedBy("SYSTEM");
        transaction.setUpdatedBy("SYSTEM");

        inventoryDAO.save(em, transaction);
    }

    @Override
    public InventoryTransactionDTO getTransactionById(Long id) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            InventoryTransaction transaction =
                    inventoryDAO.findById(em, id);

            if (transaction == null) {
                throw new ResourceNotFoundException(
                        "Inventory transaction not found.");
            }

            return InventoryTransactionMapper.toDTO(transaction);

        } finally {

            em.close();

        }

    }

    @Override
    public List<InventoryTransactionDTO> getAllTransactions() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return inventoryDAO.findAll(em)
                    .stream()
                    .map(InventoryTransactionMapper::toDTO)
                    .collect(Collectors.toList());

        } finally {

            em.close();

        }

    }

    @Override
    public List<InventoryTransactionDTO> getTransactionsByProduct(
            Long productId) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return inventoryDAO.findByProduct(em, productId)
                    .stream()
                    .map(InventoryTransactionMapper::toDTO)
                    .collect(Collectors.toList());

        } finally {

            em.close();

        }

    }

    @Override
    public List<InventoryTransactionDTO> getTransactionsByType(
            com.inventotrack.enums.InventoryTransactionType type) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return inventoryDAO.findByTransactionType(em, type)
                    .stream()
                    .map(InventoryTransactionMapper::toDTO)
                    .collect(Collectors.toList());

        } finally {

            em.close();

        }

    }

    @Override
    public List<InventoryTransactionDTO> getTransactionsByReference(
            String referenceType,
            Long referenceId) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return inventoryDAO
                    .findByReference(em,
                            referenceType,
                            referenceId)
                    .stream()
                    .map(InventoryTransactionMapper::toDTO)
                    .collect(Collectors.toList());

        } finally {

            em.close();

        }

    }

}