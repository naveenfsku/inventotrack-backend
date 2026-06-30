package com.inventotrack.dao;

import com.inventotrack.dto.*;
import com.inventotrack.model.CustomerOrder;

import jakarta.persistence.EntityManager;

import java.util.List;

public interface ReportDAO {

    RevenueReportDTO getRevenueReport(EntityManager em);

    SalesReportDTO getSalesReport(EntityManager em);

    InventoryReportDTO getInventoryReport(EntityManager em);

    List<TopProductDTO> getTopSellingProducts(EntityManager em);

    List<OrderSummaryDTO> getRecentOrders(EntityManager em);

}