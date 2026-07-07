package com.inventotrack.service.impl;

import com.inventotrack.dao.DashboardDAO;
import com.inventotrack.dto.DashboardDTO;
import com.inventotrack.service.DashboardService;
import com.inventotrack.util.JPAUtil;

import com.inventotrack.util.LoggerUtil;
import jakarta.persistence.EntityManager;

import java.util.logging.Logger;

public class DashboardServiceImpl
        implements DashboardService {

    private final DashboardDAO dashboardDAO;
    private static final Logger logger =
            LoggerUtil.getLogger(OrderServiceImpl.class);

    public DashboardServiceImpl(DashboardDAO dashboardDAO) {
        this.dashboardDAO = dashboardDAO;
    }

    @Override
    public DashboardDTO getDashboardStatistics() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return dashboardDAO.getDashboardStatistics(em);

        } finally {

            em.close();
            logger.info("Goes to Dashboard.");

        }

    }

}