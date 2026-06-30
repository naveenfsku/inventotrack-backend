package com.inventotrack.dto;

import com.inventotrack.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderResponse {

    private Long id;
    private String username;
    private OrderStatus status;
    private BigDecimal totalAmount;
    private String notes;
    private LocalDateTime createdAt;
    private List<OrderItemResponse> items = new ArrayList<>();

    public OrderResponse() {
    }

    public OrderResponse(Long id,
                         String username,
                         OrderStatus status,
                         BigDecimal totalAmount,
                         String notes,
                         LocalDateTime createdAt,
                         List<OrderItemResponse> items) {
        this.id = id;
        this.username = username;
        this.status = status;
        this.totalAmount = totalAmount;
        this.notes = notes;
        this.createdAt = createdAt;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public String getNotes() {
        return notes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<OrderItemResponse> getItems() {
        return items;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setItems(List<OrderItemResponse> items) {
        this.items = items;
    }
}