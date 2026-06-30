package com.inventotrack.dao.impl;

import com.inventotrack.dao.ReportDAO;
import com.inventotrack.dto.*;
import com.inventotrack.enums.OrderStatus;
import com.inventotrack.model.CustomerOrder;
import com.inventotrack.model.OrderItem;
import com.inventotrack.model.Product;

import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class ReportDAOImpl implements ReportDAO {

    @Override
    public RevenueReportDTO getRevenueReport(EntityManager em) {

        RevenueReportDTO dto = new RevenueReportDTO();

        BigDecimal revenue = em.createQuery(
                        "SELECT SUM(o.totalAmount) FROM CustomerOrder o WHERE o.status = :status",
                        BigDecimal.class)
                .setParameter("status", OrderStatus.COMPLETED)
                .getSingleResult();

        Long completed = em.createQuery(
                        "SELECT COUNT(o) FROM CustomerOrder o WHERE o.status = :status",
                        Long.class)
                .setParameter("status", OrderStatus.COMPLETED)
                .getSingleResult();

        dto.setTotalRevenue(revenue == null ? BigDecimal.ZERO : revenue);
        dto.setCompletedOrders(completed);

        if (completed == null || completed == 0) {

            dto.setAverageOrderValue(BigDecimal.ZERO);

        } else {

            dto.setAverageOrderValue(
                    dto.getTotalRevenue().divide(
                            BigDecimal.valueOf(completed),
                            2,
                            RoundingMode.HALF_UP
                    )
            );

        }

        return dto;
    }

    @Override
    public SalesReportDTO getSalesReport(EntityManager em) {

        SalesReportDTO dto = new SalesReportDTO();

        dto.setTotalOrders(
                em.createQuery(
                                "SELECT COUNT(o) FROM CustomerOrder o",
                                Long.class)
                        .getSingleResult()
        );

        dto.setPendingOrders(getCount(em, OrderStatus.PENDING));
        dto.setProcessingOrders(getCount(em, OrderStatus.PROCESSING));
        dto.setCompletedOrders(getCount(em, OrderStatus.COMPLETED));
        dto.setCancelledOrders(getCount(em, OrderStatus.CANCELLED));

        return dto;
    }

    @Override
    public InventoryReportDTO getInventoryReport(EntityManager em) {

        InventoryReportDTO dto = new InventoryReportDTO();

        dto.setTotalProducts(
                em.createQuery(
                                "SELECT COUNT(p) FROM Product p",
                                Long.class)
                        .getSingleResult()
        );

        dto.setActiveProducts(
                em.createQuery(
                                "SELECT COUNT(p) FROM Product p WHERE p.active = true",
                                Long.class)
                        .getSingleResult()
        );

        dto.setInactiveProducts(
                em.createQuery(
                                "SELECT COUNT(p) FROM Product p WHERE p.active = false",
                                Long.class)
                        .getSingleResult()
        );

        dto.setLowStockProducts(
                em.createQuery(
                                "SELECT COUNT(p) FROM Product p WHERE p.stockQuantity <= p.lowStockThreshold",
                                Long.class)
                        .getSingleResult()
        );

        dto.setOutOfStockProducts(
                em.createQuery(
                                "SELECT COUNT(p) FROM Product p WHERE p.stockQuantity = 0",
                                Long.class)
                        .getSingleResult()
        );

        BigDecimal value = em.createQuery(
                        "SELECT SUM(p.price * p.stockQuantity) FROM Product p",
                        BigDecimal.class)
                .getSingleResult();

        dto.setInventoryValue(
                value == null ? BigDecimal.ZERO : value
        );

        return dto;
    }

    @Override
    public List<TopProductDTO> getTopSellingProducts(EntityManager em) {

        return em.createQuery(
                        "SELECT new com.inventotrack.dto.TopProductDTO(" +
                                "oi.product.id, " +
                                "oi.product.name, " +
                                "SUM(oi.quantity)) " +
                                "FROM OrderItem oi " +
                                "GROUP BY oi.product.id, oi.product.name " +
                                "ORDER BY SUM(oi.quantity) DESC",
                        TopProductDTO.class)
                .setMaxResults(5)
                .getResultList();

    }

    @Override
    public List<OrderSummaryDTO> getRecentOrders(EntityManager em) {

        return em.createQuery(

                        "SELECT new com.inventotrack.dto.OrderSummaryDTO(" +
                                "o.id, " +
                                "o.user.username, " +
                                "o.status, " +
                                "o.totalAmount, " +
                                "o.createdAt) " +
                                "FROM CustomerOrder o " +
                                "ORDER BY o.createdAt DESC",

                        OrderSummaryDTO.class)

                .setMaxResults(10)

                .getResultList();

    }

    private Long getCount(EntityManager em,
                          OrderStatus status) {

        return em.createQuery(
                        "SELECT COUNT(o) FROM CustomerOrder o WHERE o.status = :status",
                        Long.class)
                .setParameter("status", status)
                .getSingleResult();

    }
}