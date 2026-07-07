package com.inventotrack.validation;

import com.inventotrack.dto.SupplierDTO;
import com.inventotrack.exception.ValidationException;

public final class SupplierValidator {

    private SupplierValidator() {
    }

    public static void validate(SupplierDTO dto) {

        if (dto == null) {
            throw new ValidationException("Supplier cannot be null.");
        }

        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new ValidationException("Supplier name is required.");
        }

        if (dto.getEmail() == null || dto.getEmail().isBlank()) {
            throw new ValidationException("Supplier email is required.");
        }

        if (dto.getPhone() == null || dto.getPhone().isBlank()) {
            throw new ValidationException("Supplier phone is required.");
        }

    }

}