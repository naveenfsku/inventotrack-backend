package com.inventotrack.service;

import com.inventotrack.dto.SupplierDTO;

import java.util.List;

public interface SupplierService {

    SupplierDTO createSupplier(SupplierDTO dto);

    SupplierDTO updateSupplier(Long id,
                               SupplierDTO dto);

    void deleteSupplier(Long id);

    SupplierDTO getSupplierById(Long id);

    List<SupplierDTO> getAllSuppliers();

    List<SupplierDTO> searchSuppliers(String keyword);

}