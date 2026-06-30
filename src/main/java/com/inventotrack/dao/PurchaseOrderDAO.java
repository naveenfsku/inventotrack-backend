package com.inventotrack.dao;

import com.inventotrack.enums.PurchaseOrderStatus;
import com.inventotrack.model.PurchaseOrder;
import jakarta.persistence.EntityManager;

import java.util.List;

public interface PurchaseOrderDAO extends BaseDAO<PurchaseOrder> {

    List<PurchaseOrder> findAllPurchaseOrders(EntityManager em);

    List<PurchaseOrder> findBySupplier(EntityManager em,
                                       Long supplierId);

    List<PurchaseOrder> findByStatus(EntityManager em,
                                     PurchaseOrderStatus status);

}