package com.inventotrack.dao;

import com.inventotrack.dto.DashboardDTO;

import jakarta.persistence.EntityManager;

public interface DashboardDAO {

    DashboardDTO getDashboardStatistics(EntityManager em);

}