package com.inventotrack.dao;

import com.inventotrack.model.OrderItem;
import jakarta.persistence.EntityManager;

import java.util.List;

public interface OrderItemDAO extends BaseDAO<OrderItem> {

    List<OrderItem> findByOrder(EntityManager em, Long orderId);

}