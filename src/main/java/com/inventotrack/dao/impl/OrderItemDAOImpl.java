package com.inventotrack.dao.impl;

import com.inventotrack.dao.OrderItemDAO;
import com.inventotrack.model.OrderItem;
import jakarta.persistence.EntityManager;

import java.util.List;

public class OrderItemDAOImpl
        extends AbstractDAOImpl<OrderItem>
        implements OrderItemDAO {

    public OrderItemDAOImpl() {
        super(OrderItem.class);
    }

    @Override
    public List<OrderItem> findByOrder(EntityManager em, Long orderId) {

        return em.createQuery(
                        "SELECT oi FROM OrderItem oi WHERE oi.order.id = :orderId",
                        OrderItem.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }
}