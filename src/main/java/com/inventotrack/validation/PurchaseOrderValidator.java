package com.inventotrack.validation;

import com.inventotrack.dto.PurchaseOrderDTO;
import com.inventotrack.exception.ValidationException;

public final class PurchaseOrderValidator {

    private PurchaseOrderValidator() {
    }

    public static void validate(PurchaseOrderDTO dto) {

        if (dto == null) {
            throw new ValidationException(
                    "Purchase Order cannot be null.");
        }

        if (dto.getSupplierId() == null) {
            throw new ValidationException(
                    "Supplier is required.");
        }

        if (dto.getItems() == null ||
                dto.getItems().isEmpty()) {

            throw new ValidationException(
                    "Purchase Order must contain at least one item.");
        }

    }

}