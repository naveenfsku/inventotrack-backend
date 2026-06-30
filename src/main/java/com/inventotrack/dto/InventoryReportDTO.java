package com.inventotrack.dto;

import java.math.BigDecimal;

public class InventoryReportDTO {

    private Long totalProducts;
    private Long activeProducts;
    private Long inactiveProducts;
    private Long lowStockProducts;
    private Long outOfStockProducts;
    private BigDecimal inventoryValue;

    public InventoryReportDTO() {
    }

    public Long getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(Long totalProducts) {
        this.totalProducts = totalProducts;
    }

    public Long getActiveProducts() {
        return activeProducts;
    }

    public void setActiveProducts(Long activeProducts) {
        this.activeProducts = activeProducts;
    }

    public Long getInactiveProducts() {
        return inactiveProducts;
    }

    public void setInactiveProducts(Long inactiveProducts) {
        this.inactiveProducts = inactiveProducts;
    }

    public Long getLowStockProducts() {
        return lowStockProducts;
    }

    public void setLowStockProducts(Long lowStockProducts) {
        this.lowStockProducts = lowStockProducts;
    }

    public Long getOutOfStockProducts() {
        return outOfStockProducts;
    }

    public void setOutOfStockProducts(Long outOfStockProducts) {
        this.outOfStockProducts = outOfStockProducts;
    }

    public BigDecimal getInventoryValue() {
        return inventoryValue;
    }

    public void setInventoryValue(BigDecimal inventoryValue) {
        this.inventoryValue = inventoryValue;
    }
}