package com.inventotrack.dto;

import java.math.BigDecimal;

public class DashboardSummaryDTO {

    private Long totalProducts;
    private Long totalOrders;
    private Long pendingOrders;
    private BigDecimal revenue;

    public DashboardSummaryDTO() {
    }

    public DashboardSummaryDTO(Long totalProducts,
                               Long totalOrders,
                               Long pendingOrders,
                               BigDecimal revenue) {
        this.totalProducts = totalProducts;
        this.totalOrders = totalOrders;
        this.pendingOrders = pendingOrders;
        this.revenue = revenue;
    }

    public Long getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(Long totalProducts) {
        this.totalProducts = totalProducts;
    }

    public Long getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(Long totalOrders) {
        this.totalOrders = totalOrders;
    }

    public Long getPendingOrders() {
        return pendingOrders;
    }

    public void setPendingOrders(Long pendingOrders) {
        this.pendingOrders = pendingOrders;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }
}