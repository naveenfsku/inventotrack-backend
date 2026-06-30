package com.inventotrack.dao.impl;

import com.inventotrack.dao.DashboardDAO;
import com.inventotrack.dto.DashboardDTO;
import com.inventotrack.enums.OrderStatus;

import jakarta.persistence.EntityManager;

import java.math.BigDecimal;

public class DashboardDAOImpl implements DashboardDAO {

    @Override
    public DashboardDTO getDashboardStatistics(EntityManager em) {

        DashboardDTO dto = new DashboardDTO();

        // ==========================
        // Product Statistics
        // ==========================

        dto.setTotalProducts(

                em.createQuery(
                                "SELECT COUNT(p) FROM Product p",
                                Long.class)
                        .getSingleResult()

        );

        dto.setActiveProducts(

                em.createQuery(
                                "SELECT COUNT(p) FROM Product p WHERE p.active=true",
                                Long.class)
                        .getSingleResult()

        );

        dto.setInactiveProducts(

                em.createQuery(
                                "SELECT COUNT(p) FROM Product p WHERE p.active=false",
                                Long.class)
                        .getSingleResult()

        );

        dto.setLowStockProducts(

                em.createQuery(
                                "SELECT COUNT(p) FROM Product p WHERE p.stockQuantity <= p.lowStockThreshold",
                                Long.class)
                        .getSingleResult()

        );

        // ==========================
        // Order Statistics
        // ==========================

        dto.setTotalOrders(

                em.createQuery(
                                "SELECT COUNT(o) FROM CustomerOrder o",
                                Long.class)
                        .getSingleResult()

        );

        dto.setPendingOrders(

                em.createQuery(
                                "SELECT COUNT(o) FROM CustomerOrder o WHERE o.status=:status",
                                Long.class)
                        .setParameter("status", OrderStatus.PENDING)
                        .getSingleResult()

        );

        dto.setProcessingOrders(

                em.createQuery(
                                "SELECT COUNT(o) FROM CustomerOrder o WHERE o.status=:status",
                                Long.class)
                        .setParameter("status", OrderStatus.PROCESSING)
                        .getSingleResult()

        );

        dto.setCompletedOrders(

                em.createQuery(
                                "SELECT COUNT(o) FROM CustomerOrder o WHERE o.status=:status",
                                Long.class)
                        .setParameter("status", OrderStatus.COMPLETED)
                        .getSingleResult()

        );

        dto.setCancelledOrders(

                em.createQuery(
                                "SELECT COUNT(o) FROM CustomerOrder o WHERE o.status=:status",
                                Long.class)
                        .setParameter("status", OrderStatus.CANCELLED)
                        .getSingleResult()

        );

        // ==========================
        // Revenue
        // ==========================

        BigDecimal revenue =

                em.createQuery(
                                "SELECT SUM(o.totalAmount) FROM CustomerOrder o WHERE o.status=:status",
                                BigDecimal.class)
                        .setParameter("status", OrderStatus.COMPLETED)
                        .getSingleResult();

        dto.setTotalRevenue(

                revenue == null
                        ? BigDecimal.ZERO
                        : revenue

        );

        return dto;
    }
}