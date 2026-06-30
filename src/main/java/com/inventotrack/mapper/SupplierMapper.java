package com.inventotrack.mapper;

import com.inventotrack.dto.SupplierDTO;
import com.inventotrack.model.Supplier;

public class SupplierMapper {

    private SupplierMapper() {
    }

    public static SupplierDTO toDTO(Supplier supplier) {

        if (supplier == null) {
            return null;
        }

        SupplierDTO dto = new SupplierDTO();

        dto.setId(supplier.getId());
        dto.setName(supplier.getName());
        dto.setContactPerson(supplier.getContactPerson());
        dto.setEmail(supplier.getEmail());
        dto.setPhone(supplier.getPhone());
        dto.setAddress(supplier.getAddress());
        dto.setActive(supplier.isActive());

        return dto;
    }

    public static Supplier toEntity(SupplierDTO dto) {

        if (dto == null) {
            return null;
        }

        Supplier supplier = new Supplier();

        supplier.setName(dto.getName());
        supplier.setContactPerson(dto.getContactPerson());
        supplier.setEmail(dto.getEmail());
        supplier.setPhone(dto.getPhone());
        supplier.setAddress(dto.getAddress());
        supplier.setActive(dto.isActive());

        return supplier;
    }
}