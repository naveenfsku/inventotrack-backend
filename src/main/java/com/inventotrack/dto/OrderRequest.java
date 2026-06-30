package com.inventotrack.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderRequest {

    private String notes;
    private List<OrderItemRequest> items = new ArrayList<>();

    public OrderRequest() {
    }

    public OrderRequest(String notes, List<OrderItemRequest> items) {
        this.notes = notes;
        this.items = items;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<OrderItemRequest> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequest> items) {
        this.items = items;
    }
}