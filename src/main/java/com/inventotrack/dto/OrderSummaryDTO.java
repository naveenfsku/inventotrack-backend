package com.inventotrack.dto;

import com.inventotrack.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderSummaryDTO {

    private Long orderId;
    private String customerName;
    private OrderStatus status;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;

    public OrderSummaryDTO() {
    }

    public OrderSummaryDTO(Long orderId,
                           String customerName,
                           OrderStatus status,
                           BigDecimal totalAmount,
                           LocalDateTime createdAt) {

        this.orderId = orderId;
        this.customerName = customerName;
        this.status = status;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}