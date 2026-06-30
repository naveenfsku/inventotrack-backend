package com.inventotrack.service;

import com.inventotrack.dto.PurchaseOrderDTO;
import com.inventotrack.enums.PurchaseOrderStatus;

import java.util.List;

public interface PurchaseOrderService {

    PurchaseOrderDTO createPurchaseOrder(PurchaseOrderDTO dto);

    PurchaseOrderDTO getPurchaseOrderById(Long id);

    List<PurchaseOrderDTO> getAllPurchaseOrders();

    List<PurchaseOrderDTO> getPurchaseOrdersBySupplier(Long supplierId);

    List<PurchaseOrderDTO> getPurchaseOrdersByStatus(PurchaseOrderStatus status);

    PurchaseOrderDTO updatePurchaseOrderStatus(Long id,
                                               PurchaseOrderStatus status);

    void deletePurchaseOrder(Long id);

}