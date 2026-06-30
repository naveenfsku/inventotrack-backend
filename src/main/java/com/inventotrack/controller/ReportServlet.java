package com.inventotrack.controller;

import com.inventotrack.factory.ServiceFactory;
import com.inventotrack.service.ReportService;
import com.inventotrack.util.ResponseUtil;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/reports")
public class ReportServlet extends HttpServlet {

    private final ReportService reportService =
            ServiceFactory.getReportService();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws IOException {

        try {

            String type = req.getParameter("type");

            if (type == null) {

                ResponseUtil.error(resp,
                        "Report type is required");

                return;

            }

            switch (type.toLowerCase()) {

                case "revenue":

                    ResponseUtil.success(
                            resp,
                            "Revenue Report",
                            reportService.getRevenueReport());
                    break;

                case "sales":

                    ResponseUtil.success(
                            resp,
                            "Sales Report",
                            reportService.getSalesReport());
                    break;

                case "inventory":

                    ResponseUtil.success(
                            resp,
                            "Inventory Report",
                            reportService.getInventoryReport());
                    break;

                case "top-products":

                    ResponseUtil.success(
                            resp,
                            "Top Selling Products",
                            reportService.getTopSellingProducts());
                    break;

                case "recent-orders":

                    ResponseUtil.success(
                            resp,
                            "Recent Orders",
                            reportService.getRecentOrders());
                    break;

                default:

                    ResponseUtil.error(resp,
                            "Unknown report type");

            }

        } catch (Exception e) {

            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            ResponseUtil.error(resp,
                    e.getMessage());

        }

    }

}