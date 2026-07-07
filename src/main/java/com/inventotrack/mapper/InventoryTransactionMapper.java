package com.inventotrack.mapper;

import com.inventotrack.dto.InventoryTransactionDTO;
import com.inventotrack.model.InventoryTransaction;

public final class InventoryTransactionMapper {

    private InventoryTransactionMapper() {
    }

    public static InventoryTransactionDTO toDTO(
            InventoryTransaction entity) {

        if (entity == null) {
            return null;
        }

        InventoryTransactionDTO dto =
                new InventoryTransactionDTO();

        dto.setId(entity.getId());

        dto.setProductId(entity.getProduct().getId());

        dto.setProductName(entity.getProduct().getName());

        dto.setTransactionType(
                entity.getTransactionType());

        dto.setQuantity(entity.getQuantity());

        dto.setPreviousStock(
                entity.getPreviousStock());

        dto.setCurrentStock(
                entity.getCurrentStock());

        dto.setReferenceType(
                entity.getReferenceType());

        dto.setReferenceId(
                entity.getReferenceId());

        dto.setRemarks(
                entity.getRemarks());

        dto.setTransactionDate(
                entity.getTransactionDate());

        return dto;
    }

    public static InventoryTransaction toEntity(
            InventoryTransactionDTO dto) {

        if (dto == null) {
            return null;
        }

        InventoryTransaction entity =
                new InventoryTransaction();

        entity.setTransactionType(
                dto.getTransactionType());

        entity.setQuantity(
                dto.getQuantity());

        entity.setPreviousStock(
                dto.getPreviousStock());

        entity.setCurrentStock(
                dto.getCurrentStock());

        entity.setReferenceType(
                dto.getReferenceType());

        entity.setReferenceId(
                dto.getReferenceId());

        entity.setRemarks(
                dto.getRemarks());

        entity.setTransactionDate(
                dto.getTransactionDate());

        return entity;
    }

}