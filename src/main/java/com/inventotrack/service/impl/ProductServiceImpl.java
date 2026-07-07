package com.inventotrack.service.impl;

import com.inventotrack.dao.ProductDAO;
import com.inventotrack.dto.ProductDTO;
import com.inventotrack.exception.ResourceNotFoundException;
import com.inventotrack.service.ProductService;
import com.inventotrack.exception.DuplicateResourceException;
import com.inventotrack.mapper.ProductMapper;
import com.inventotrack.model.Product;
import com.inventotrack.util.JPAUtil;
import com.inventotrack.validation.ProductValidator;

import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.logging.Logger;
import com.inventotrack.util.LoggerUtil;

public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    private static final Logger logger =
            LoggerUtil.getLogger(ProductServiceImpl.class);

    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public ProductDTO createProduct(ProductDTO dto) {

        ProductValidator.validate(dto);

        EntityManager em = JPAUtil.getEntityManager();

        try {

            Product existing = productDAO.findByName(em, dto.getName());

            if (existing != null) {
                throw new DuplicateResourceException(
                        "Product already exists with name: "
                                + dto.getName());
            }

            Product product = ProductMapper.toEntity(dto);

            // Temporary audit values
            product.setCreatedBy("SYSTEM");
            product.setUpdatedBy("SYSTEM");

            em.getTransaction().begin();

            productDAO.save(em, product);

            em.getTransaction().commit();

            return ProductMapper.toDTO(product);

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw e;

        } finally {

            em.close();

        }
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO dto) {

        ProductValidator.validate(dto);

        EntityManager em = JPAUtil.getEntityManager();

        try {

            Product product = productDAO.findById(em, id);

            if (product == null) {
                throw new ResourceNotFoundException(
                        "Product not found with id: " + id);
            }

            Product existing = productDAO.findByName(em, dto.getName());

            if (existing != null && !existing.getId().equals(id)) {
                throw new DuplicateResourceException(
                        "Another product already exists with name: "
                                + dto.getName());
            }

            ProductMapper.updateEntity(product, dto);

            product.setUpdatedBy("SYSTEM");

            em.getTransaction().begin();

            productDAO.update(em, product);

            em.getTransaction().commit();

            return ProductMapper.toDTO(product);

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw e;

        } finally {

            em.close();

        }

    }

    @Override
    public void deleteProduct(Long id) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            Product product = productDAO.findById(em, id);

            if (product == null) {
                throw new ResourceNotFoundException(
                        "Product not found with id: " + id);
            }

            em.getTransaction().begin();

            product.setActive(false);
            product.setUpdatedBy("SYSTEM");

            productDAO.update(em, product);

            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw e;

        } finally {

            em.close();

        }

    }

    @Override
    public ProductDTO getProductById(Long id) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            Product product = productDAO.findById(em, id);

            if (product == null || !product.isActive()) {
                throw new ResourceNotFoundException(
                        "Product not found with id: " + id);
            }

            return ProductMapper.toDTO(product);

        } finally {

            em.close();

        }
    }

    @Override
    public List<ProductDTO> getAllProducts() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return productDAO.findActiveProducts(em)
                    .stream()
                    .map(ProductMapper::toDTO)
                    .collect(java.util.stream.Collectors.toList());

        } finally {

            em.close();

        }

    }

    @Override
    public List<ProductDTO> searchProducts(String keyword) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return productDAO.search(em, keyword)
                    .stream()
                    .map(ProductMapper::toDTO)
                    .collect(java.util.stream.Collectors.toList());

        } finally {

            em.close();

        }

    }

    @Override
    public List<ProductDTO> getLowStockProducts() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return productDAO.findLowStockProducts(em)
                    .stream()
                    .map(ProductMapper::toDTO)
                    .collect(java.util.stream.Collectors.toList());

        } finally {

            em.close();

        }
    }
}