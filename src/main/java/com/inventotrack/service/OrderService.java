package com.inventotrack.service;

import com.inventotrack.dto.OrderDTO;
import com.inventotrack.enums.OrderStatus;

import java.util.List;

public interface OrderService {

    OrderDTO createOrder(OrderDTO dto);

    OrderDTO getOrderById(Long id);

    List<OrderDTO> getAllOrders();

    List<OrderDTO> getOrdersByUser(Long userId);

    List<OrderDTO> getOrdersByStatus(OrderStatus status);

    OrderDTO updateOrderStatus(Long id, OrderStatus status);

    void deleteOrder(Long id);

    java.math.BigDecimal getTotalRevenue();

    Long getPendingOrdersCount();
}