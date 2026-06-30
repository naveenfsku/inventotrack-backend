package com.inventotrack.service;

import com.inventotrack.dto.*;
import com.inventotrack.model.CustomerOrder;

import java.util.List;

public interface ReportService {

    RevenueReportDTO getRevenueReport();

    SalesReportDTO getSalesReport();

    InventoryReportDTO getInventoryReport();

    List<TopProductDTO> getTopSellingProducts();

    List<OrderSummaryDTO> getRecentOrders();

}