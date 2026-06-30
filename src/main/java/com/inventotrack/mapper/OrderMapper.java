package com.inventotrack.mapper;

import com.inventotrack.dto.OrderDTO;
import com.inventotrack.dto.OrderItemDTO;
import com.inventotrack.model.CustomerOrder;
import com.inventotrack.model.OrderItem;

import java.util.List;
import java.util.stream.Collectors;

public final class OrderMapper {

    private OrderMapper() {
    }

    public static OrderDTO toDTO(CustomerOrder order) {

        if (order == null) {
            return null;
        }

        OrderDTO dto = new OrderDTO();

        dto.setId(order.getId());

        dto.setUserId(order.getUser().getId());

        dto.setUsername(order.getUser().getUsername());

        dto.setStatus(order.getStatus());

        dto.setTotalAmount(order.getTotalAmount());

        dto.setNotes(order.getNotes());

        dto.setItems(

                order.getItems()
                        .stream()
                        .map(OrderMapper::toItemDTO)
                        .collect(Collectors.toList())

        );

        return dto;
    }

    public static OrderItemDTO toItemDTO(OrderItem item) {

        if (item == null) {
            return null;
        }

        OrderItemDTO dto = new OrderItemDTO();

        dto.setProductId(item.getProduct().getId());

        dto.setProductName(item.getProduct().getName());

        dto.setQuantity(item.getQuantity());

        dto.setUnitPrice(item.getUnitPrice());

        dto.setLineTotal(item.getLineTotal());

        return dto;
    }

    public static List<OrderDTO> toDTOList(List<CustomerOrder> orders) {

        return orders.stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());

    }

}