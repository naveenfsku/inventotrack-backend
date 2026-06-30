package com.inventotrack.validation;

import com.inventotrack.dto.OrderDTO;
import com.inventotrack.dto.OrderItemDTO;

public final class OrderValidator {

    private OrderValidator() {
    }

    public static void validate(OrderDTO dto) {

        if (dto == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }

        if (dto.getUserId() == null) {
            throw new IllegalArgumentException("User is required.");
        }

        if (dto.getItems() == null || dto.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item.");
        }

        for (OrderItemDTO item : dto.getItems()) {

            if (item.getProductId() == null) {
                throw new IllegalArgumentException("Product is required.");
            }

            if (item.getQuantity() == null || item.getQuantity() <= 0) {
                throw new IllegalArgumentException(
                        "Quantity must be greater than zero."
                );
            }
        }

    }

}