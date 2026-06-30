package com.inventotrack.dto;

public class SalesReportDTO {

    private Long totalOrders;
    private Long pendingOrders;
    private Long processingOrders;
    private Long completedOrders;
    private Long cancelledOrders;

    public SalesReportDTO() {
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

    public Long getProcessingOrders() {
        return processingOrders;
    }

    public void setProcessingOrders(Long processingOrders) {
        this.processingOrders = processingOrders;
    }

    public Long getCompletedOrders() {
        return completedOrders;
    }

    public void setCompletedOrders(Long completedOrders) {
        this.completedOrders = completedOrders;
    }

    public Long getCancelledOrders() {
        return cancelledOrders;
    }

    public void setCancelledOrders(Long cancelledOrders) {
        this.cancelledOrders = cancelledOrders;
    }
}