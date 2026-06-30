package com.inventotrack.dao;

import com.inventotrack.enums.OrderStatus;
import com.inventotrack.model.CustomerOrder;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;

public interface CustomerOrderDAO extends BaseDAO<CustomerOrder> {

    List<CustomerOrder> findByUser(EntityManager em, Long userId);

    List<CustomerOrder> findAllOrders(EntityManager em);

    List<CustomerOrder> findByStatus(EntityManager em,
                                     OrderStatus status);

    BigDecimal calculateRevenue(EntityManager em);

    Long countPendingOrders(EntityManager em);
}