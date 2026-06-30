package com.inventotrack.controller;

import com.inventotrack.dto.OrderDTO;
import com.inventotrack.enums.OrderStatus;
import com.inventotrack.factory.ServiceFactory;
import com.inventotrack.service.OrderService;
import com.inventotrack.util.JsonUtil;
import com.inventotrack.util.ResponseUtil;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/orders")
public class OrderServlet extends HttpServlet {

    private final OrderService orderService =
            ServiceFactory.getOrderService();

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws IOException {

        try {

            OrderDTO dto =
                    JsonUtil.readJson(req, OrderDTO.class);

            OrderDTO saved =
                    orderService.createOrder(dto);

            resp.setStatus(HttpServletResponse.SC_CREATED);

            ResponseUtil.success(
                    resp,
                    "Order created successfully",
                    saved);

        } catch (Exception e) {

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            ResponseUtil.error(resp, e.getMessage());

        }

    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws IOException {

        try {

            String id = req.getParameter("id");
            String userId = req.getParameter("userId");
            String status = req.getParameter("status");

            if (id != null) {

                ResponseUtil.success(
                        resp,
                        "Order fetched successfully",
                        orderService.getOrderById(
                                Long.parseLong(id)));

                return;
            }

            if (userId != null) {

                ResponseUtil.success(
                        resp,
                        "User orders fetched successfully",
                        orderService.getOrdersByUser(
                                Long.parseLong(userId)));

                return;
            }

            if (status != null) {

                ResponseUtil.success(
                        resp,
                        "Orders fetched successfully",
                        orderService.getOrdersByStatus(
                                OrderStatus.valueOf(status)));

                return;
            }

            ResponseUtil.success(
                    resp,
                    "Orders fetched successfully",
                    orderService.getAllOrders());

        } catch (Exception e) {

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            ResponseUtil.error(resp, e.getMessage());

        }

    }

    @Override
    protected void doPut(HttpServletRequest req,
                         HttpServletResponse resp)
            throws IOException {

        try {

            String id = req.getParameter("id");
            String status = req.getParameter("status");

            if (id == null || status == null) {

                ResponseUtil.error(
                        resp,
                        "Order ID and Status are required");

                return;
            }

            ResponseUtil.success(
                    resp,
                    "Order updated successfully",
                    orderService.updateOrderStatus(
                            Long.parseLong(id),
                            OrderStatus.valueOf(status)));

        } catch (Exception e) {

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            ResponseUtil.error(resp, e.getMessage());

        }

    }

    @Override
    protected void doDelete(HttpServletRequest req,
                            HttpServletResponse resp)
            throws IOException {

        try {

            String id = req.getParameter("id");

            if (id == null) {

                ResponseUtil.error(
                        resp,
                        "Order ID is required");

                return;
            }

            orderService.deleteOrder(
                    Long.parseLong(id));

            ResponseUtil.success(
                    resp,
                    "Order cancelled successfully",
                    null);

        } catch (Exception e) {

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            ResponseUtil.error(resp, e.getMessage());

        }

    }

}