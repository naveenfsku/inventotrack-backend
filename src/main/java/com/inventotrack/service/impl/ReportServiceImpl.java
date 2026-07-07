package com.inventotrack.service.impl;

import com.inventotrack.dao.ReportDAO;
import com.inventotrack.dto.*;
import com.inventotrack.model.CustomerOrder;
import com.inventotrack.service.ReportService;
import com.inventotrack.util.JPAUtil;

import com.inventotrack.util.LoggerUtil;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.logging.Logger;

public class ReportServiceImpl implements ReportService {

    private final ReportDAO reportDAO;
    private static final Logger logger =
            LoggerUtil.getLogger(OrderServiceImpl.class);

    public ReportServiceImpl(ReportDAO reportDAO) {
        this.reportDAO = reportDAO;
    }

    @Override
    public RevenueReportDTO getRevenueReport() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return reportDAO.getRevenueReport(em);

        } finally {

            em.close();
            logger.info("Get revenue report .");

        }

    }

    @Override
    public SalesReportDTO getSalesReport() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return reportDAO.getSalesReport(em);

        } finally {

            em.close();
            logger.info("sales report.");

        }

    }

    @Override
    public InventoryReportDTO getInventoryReport() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return reportDAO.getInventoryReport(em);

        } finally {

            em.close();
            logger.info("Inventory report.");

        }

    }

    @Override
    public List<TopProductDTO> getTopSellingProducts() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return reportDAO.getTopSellingProducts(em);

        } finally {

            em.close();
            logger.info("Top selling products report.");

        }

    }

    @Override
    public List<OrderSummaryDTO> getRecentOrders() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return reportDAO.getRecentOrders(em);

        } finally {

            em.close();
            logger.info("Recent orders report.");

        }

    }

}