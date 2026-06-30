package com.inventotrack.mapper;

import com.inventotrack.dto.PurchaseOrderDTO;
import com.inventotrack.dto.PurchaseOrderItemDTO;
import com.inventotrack.model.PurchaseOrder;
import com.inventotrack.model.PurchaseOrderItem;

import java.util.List;
import java.util.stream.Collectors;

public final class PurchaseOrderMapper {

    private PurchaseOrderMapper() {
    }

    public static PurchaseOrderDTO toDTO(PurchaseOrder order) {

        if (order == null) {
            return null;
        }

        PurchaseOrderDTO dto = new PurchaseOrderDTO();

        dto.setId(order.getId());

        dto.setSupplierId(order.getSupplier().getId());

        dto.setSupplierName(order.getSupplier().getName());

        dto.setStatus(order.getStatus());

        dto.setOrderDate(order.getOrderDate());

        dto.setExpectedDeliveryDate(order.getExpectedDeliveryDate());

        dto.setTotalAmount(order.getTotalAmount());

        dto.setNotes(order.getNotes());

        List<PurchaseOrderItemDTO> items =
                order.getItems()
                        .stream()
                        .map(PurchaseOrderMapper::toItemDTO)
                        .collect(Collectors.toList());

        dto.setItems(items);

        return dto;
    }

    public static PurchaseOrderItemDTO toItemDTO(
            PurchaseOrderItem item) {

        PurchaseOrderItemDTO dto =
                new PurchaseOrderItemDTO();

        dto.setProductId(
                item.getProduct().getId());

        dto.setProductName(
                item.getProduct().getName());

        dto.setQuantity(
                item.getQuantity());

        dto.setUnitCost(
                item.getUnitCost());

        dto.setLineTotal(
                item.getLineTotal());

        return dto;
    }

}