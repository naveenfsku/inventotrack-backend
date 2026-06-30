package com.inventotrack.util;

import com.inventotrack.dto.ProductDTO;

public final class ValidationUtil {

    private ValidationUtil() {
    }

    public static void validateProduct(ProductDTO dto) {

        if (dto == null)
            throw new IllegalArgumentException("Product cannot be null");

        if (dto.getName() == null ||
                dto.getName().trim().isEmpty())
            throw new IllegalArgumentException("Product name is required");

        if (dto.getPrice() == null)
            throw new IllegalArgumentException("Price is required");

        if (dto.getStockQuantity() == null)
            throw new IllegalArgumentException("Stock quantity is required");

    }

}