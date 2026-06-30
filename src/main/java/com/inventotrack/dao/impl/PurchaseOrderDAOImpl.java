package com.inventotrack.dao.impl;

import com.inventotrack.dao.PurchaseOrderDAO;
import com.inventotrack.enums.PurchaseOrderStatus;
import com.inventotrack.model.PurchaseOrder;

import jakarta.persistence.EntityManager;

import java.util.List;

public class PurchaseOrderDAOImpl
        extends AbstractDAOImpl<PurchaseOrder>
        implements PurchaseOrderDAO {

    public PurchaseOrderDAOImpl() {
        super(PurchaseOrder.class);
    }

    @Override
    public List<PurchaseOrder> findAllPurchaseOrders(EntityManager em) {

        return em.createQuery(
                        "SELECT p FROM PurchaseOrder p ORDER BY p.orderDate DESC",
                        PurchaseOrder.class)
                .getResultList();

    }

    @Override
    public List<PurchaseOrder> findBySupplier(EntityManager em,
                                              Long supplierId) {

        return em.createQuery(
                        "SELECT p FROM PurchaseOrder p WHERE p.supplier.id=:id ORDER BY p.orderDate DESC",
                        PurchaseOrder.class)
                .setParameter("id", supplierId)
                .getResultList();

    }

    @Override
    public List<PurchaseOrder> findByStatus(EntityManager em,
                                            PurchaseOrderStatus status) {

        return em.createQuery(
                        "SELECT p FROM PurchaseOrder p WHERE p.status=:status ORDER BY p.orderDate DESC",
                        PurchaseOrder.class)
                .setParameter("status", status)
                .getResultList();

    }

}