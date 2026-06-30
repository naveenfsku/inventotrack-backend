package com.inventotrack.test;

import com.inventotrack.dto.ProductDTO;
import com.inventotrack.factory.ServiceFactory;
import com.inventotrack.service.ProductService;

import java.math.BigDecimal;

public class ProductServiceTest {

    public static void main(String[] args) {

        ProductService service = ServiceFactory.getProductService();

// Create
        ProductDTO dto = new ProductDTO();
        dto.setName("Dell Laptop");
        dto.setDescription("Dell Inspiron");
        dto.setCategory("Electronics");
        dto.setPrice(new BigDecimal("65000"));
        dto.setStockQuantity(20);
        dto.setLowStockThreshold(5);
        dto.setActive(true);

        ProductDTO saved = service.createProduct(dto);

        System.out.println("Created Product ID: " + saved.getId());

// Update the same product
        ProductDTO updateDTO = new ProductDTO();
        updateDTO.setName("Dell Laptop Updated");
        updateDTO.setDescription("Dell Inspiron i7");
        updateDTO.setCategory("Electronics");
        updateDTO.setPrice(new BigDecimal("70000"));
        updateDTO.setStockQuantity(25);
        updateDTO.setLowStockThreshold(5);
        updateDTO.setActive(true);

        ProductDTO updated = service.updateProduct(saved.getId(), updateDTO);

        System.out.println("Updated Name: " + updated.getName());

        ProductDTO duplicate = new ProductDTO();

        duplicate.setName("Dell Laptop Updated");
    }
}