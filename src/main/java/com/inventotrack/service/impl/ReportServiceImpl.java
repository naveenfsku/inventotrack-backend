package com.inventotrack.service.impl;

import com.inventotrack.dao.ReportDAO;
import com.inventotrack.dto.*;
import com.inventotrack.model.CustomerOrder;
import com.inventotrack.service.ReportService;
import com.inventotrack.util.JPAUtil;

import jakarta.persistence.EntityManager;

import java.util.List;

public class ReportServiceImpl implements ReportService {

    private final ReportDAO reportDAO;

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

        }

    }

    @Override
    public SalesReportDTO getSalesReport() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return reportDAO.getSalesReport(em);

        } finally {

            em.close();

        }

    }

    @Override
    public InventoryReportDTO getInventoryReport() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return reportDAO.getInventoryReport(em);

        } finally {

            em.close();

        }

    }

    @Override
    public List<TopProductDTO> getTopSellingProducts() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return reportDAO.getTopSellingProducts(em);

        } finally {

            em.close();

        }

    }

    @Override
    public List<OrderSummaryDTO> getRecentOrders() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return reportDAO.getRecentOrders(em);

        } finally {

            em.close();

        }

    }

}