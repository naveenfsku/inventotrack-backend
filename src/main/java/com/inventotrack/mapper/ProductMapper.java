package com.inventotrack.mapper;

import com.inventotrack.dto.ProductDTO;
import com.inventotrack.model.Product;

public final class ProductMapper {

    private ProductMapper() {
    }

    public static ProductDTO toDTO(Product product) {

        if (product == null)
            return null;

        return new ProductDTO(

                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getLowStockThreshold(),
                product.getCategory(),
                product.isActive(),
                product.isLowStock()

        );

    }

    public static Product toEntity(ProductDTO dto) {

        if (dto == null)
            return null;

        Product product = new Product();

        updateEntity(product, dto);

        return product;

    }

    public static void updateEntity(Product product,
                                    ProductDTO dto) {

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setLowStockThreshold(dto.getLowStockThreshold());
        product.setCategory(dto.getCategory());

        if (dto.getActive() != null)
            product.setActive(dto.getActive());

    }

}