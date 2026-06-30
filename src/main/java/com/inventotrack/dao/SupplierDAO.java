package com.inventotrack.dao;

import com.inventotrack.model.Supplier;
import jakarta.persistence.EntityManager;

import java.util.List;

public interface SupplierDAO extends BaseDAO<Supplier> {

    Supplier findByName(EntityManager em, String name);

    List<Supplier> findActiveSuppliers(EntityManager em);

    List<Supplier> search(EntityManager em, String keyword);

}