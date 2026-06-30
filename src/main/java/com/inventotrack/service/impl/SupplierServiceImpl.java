package com.inventotrack.service.impl;

import com.inventotrack.dao.SupplierDAO;
import com.inventotrack.dto.SupplierDTO;
import com.inventotrack.mapper.SupplierMapper;
import com.inventotrack.model.Supplier;
import com.inventotrack.service.SupplierService;
import com.inventotrack.util.JPAUtil;

import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.stream.Collectors;

public class SupplierServiceImpl implements SupplierService {

    private final SupplierDAO supplierDAO;

    public SupplierServiceImpl(SupplierDAO supplierDAO) {
        this.supplierDAO = supplierDAO;
    }

    @Override
    public SupplierDTO createSupplier(SupplierDTO dto) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            Supplier existing =
                    supplierDAO.findByName(em, dto.getName());

            if (existing != null) {
                throw new IllegalArgumentException(
                        "Supplier already exists.");
            }

            Supplier supplier =
                    SupplierMapper.toEntity(dto);

            supplier.setCreatedBy("SYSTEM");
            supplier.setUpdatedBy("SYSTEM");

            supplierDAO.save(em, supplier);

            em.getTransaction().commit();

            return SupplierMapper.toDTO(supplier);

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
    public SupplierDTO updateSupplier(Long id,
                                      SupplierDTO dto) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            Supplier supplier =
                    supplierDAO.findById(em, id);

            if (supplier == null) {
                throw new IllegalArgumentException(
                        "Supplier not found");
            }

            supplier.setName(dto.getName());
            supplier.setContactPerson(dto.getContactPerson());
            supplier.setEmail(dto.getEmail());
            supplier.setPhone(dto.getPhone());
            supplier.setAddress(dto.getAddress());
            supplier.setActive(dto.isActive());
            supplier.setUpdatedBy("SYSTEM");

            supplierDAO.update(em, supplier);

            em.getTransaction().commit();

            return SupplierMapper.toDTO(supplier);

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
    public void deleteSupplier(Long id) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            supplierDAO.delete(em, id);

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
    public SupplierDTO getSupplierById(Long id) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return SupplierMapper.toDTO(
                    supplierDAO.findById(em, id));

        } finally {

            em.close();

        }
    }

    @Override
    public List<SupplierDTO> getAllSuppliers() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return supplierDAO.findAll(em)
                    .stream()
                    .map(SupplierMapper::toDTO)
                    .collect(Collectors.toList());

        } finally {

            em.close();

        }
    }

    @Override
    public List<SupplierDTO> searchSuppliers(String keyword) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return supplierDAO.search(em, keyword)
                    .stream()
                    .map(SupplierMapper::toDTO)
                    .collect(Collectors.toList());

        } finally {

            em.close();

        }
    }
}