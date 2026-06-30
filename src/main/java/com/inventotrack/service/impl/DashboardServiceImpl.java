package com.inventotrack.service.impl;

import com.inventotrack.dao.DashboardDAO;
import com.inventotrack.dto.DashboardDTO;
import com.inventotrack.service.DashboardService;
import com.inventotrack.util.JPAUtil;

import jakarta.persistence.EntityManager;

public class DashboardServiceImpl
        implements DashboardService {

    private final DashboardDAO dashboardDAO;

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

        }

    }

}