package com.inventotrack.validation;

import com.inventotrack.dto.ProductDTO;

public final class ProductValidator {

    private ProductValidator() {
    }

    public static void validate(ProductDTO dto) {

        if (dto == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new IllegalArgumentException("Product name is required");
        }

        if (dto.getPrice() == null || dto.getPrice().doubleValue() <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }

        if (dto.getStockQuantity() == null || dto.getStockQuantity() < 0) {
            throw new IllegalArgumentException("Invalid stock quantity");
        }

        if (dto.getLowStockThreshold() == null || dto.getLowStockThreshold() < 0) {
            throw new IllegalArgumentException("Invalid low stock threshold");
        }

    }
}