package com.inventotrack.service;

import com.inventotrack.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO createProduct(ProductDTO productDTO);

    ProductDTO updateProduct(Long id,
                             ProductDTO productDTO);

    void deleteProduct(Long id);

    ProductDTO getProductById(Long id);

    List<ProductDTO> getAllProducts();

    List<ProductDTO> searchProducts(String keyword);

    List<ProductDTO> getLowStockProducts();

}