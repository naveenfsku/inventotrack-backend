package com.inventotrack.validation;

import com.inventotrack.dto.ProductDTO;
import com.inventotrack.exception.ValidationException;

import java.math.BigDecimal;

public final class ProductValidator {

    private ProductValidator() {
    }

    public static void validate(ProductDTO dto) {

        if (dto == null) {
            throw new ValidationException("Product cannot be null.");
        }

        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new ValidationException("Product name is required.");
        }

        if (dto.getPrice() == null ||
                dto.getPrice().compareTo(BigDecimal.ZERO) <= 0) {

            throw new ValidationException("Product price must be greater than zero.");
        }

        if (dto.getStockQuantity() == null ||
                dto.getStockQuantity() < 0) {

            throw new ValidationException("Invalid stock quantity.");
        }

        if (dto.getLowStockThreshold() == null ||
                dto.getLowStockThreshold() < 0) {

            throw new ValidationException("Invalid low stock threshold.");
        }

    }

}