package com.inventotrack.controller;

import com.inventotrack.factory.ServiceFactory;
import com.inventotrack.service.DashboardService;
import com.inventotrack.util.ExceptionHandlerUtil;
import com.inventotrack.util.ResponseUtil;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/dashboard")
public class DashboardServlet extends HttpServlet {

    private final DashboardService dashboardService =
            ServiceFactory.getDashboardService();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws IOException {

        try {

            ResponseUtil.success(
                    resp,
                    "Dashboard statistics",
                    dashboardService.getDashboardStatistics());

        } catch (Exception e) {

            ExceptionHandlerUtil.handle(e, resp);

            ResponseUtil.error(
                    resp,
                    e.getMessage());

        }

    }

}