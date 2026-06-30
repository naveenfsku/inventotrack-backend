package com.inventotrack.controller;

import com.inventotrack.dto.PurchaseOrderDTO;
import com.inventotrack.enums.PurchaseOrderStatus;
import com.inventotrack.factory.ServiceFactory;
import com.inventotrack.service.PurchaseOrderService;
import com.inventotrack.util.JsonUtil;
import com.inventotrack.util.ResponseUtil;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/purchase-orders")
public class PurchaseOrderServlet extends HttpServlet {

    private final PurchaseOrderService purchaseOrderService =
            ServiceFactory.getPurchaseOrderService();

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws IOException {

        try {

            PurchaseOrderDTO dto =
                    JsonUtil.readJson(req,
                            PurchaseOrderDTO.class);

            PurchaseOrderDTO saved =
                    purchaseOrderService.createPurchaseOrder(dto);

            resp.setStatus(HttpServletResponse.SC_CREATED);

            ResponseUtil.success(
                    resp,
                    "Purchase Order created successfully",
                    saved);

        } catch (Exception e) {

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            ResponseUtil.error(resp,
                    e.getMessage());

        }

    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws IOException {

        try {

            String id = req.getParameter("id");

            String supplierId =
                    req.getParameter("supplierId");

            String status =
                    req.getParameter("status");

            if (id != null) {

                ResponseUtil.success(
                        resp,
                        "Purchase Order fetched successfully",
                        purchaseOrderService.getPurchaseOrderById(
                                Long.parseLong(id)));

                return;

            }

            if (supplierId != null) {

                ResponseUtil.success(
                        resp,
                        "Supplier Purchase Orders",
                        purchaseOrderService
                                .getPurchaseOrdersBySupplier(
                                        Long.parseLong(supplierId)));

                return;

            }

            if (status != null) {

                ResponseUtil.success(
                        resp,
                        "Purchase Orders",
                        purchaseOrderService
                                .getPurchaseOrdersByStatus(
                                        PurchaseOrderStatus.valueOf(status)));

                return;

            }

            ResponseUtil.success(
                    resp,
                    "All Purchase Orders",
                    purchaseOrderService
                            .getAllPurchaseOrders());

        } catch (Exception e) {

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            ResponseUtil.error(resp,
                    e.getMessage());

        }

    }

    @Override
    protected void doPut(HttpServletRequest req,
                         HttpServletResponse resp)
            throws IOException {

        try {

            String id = req.getParameter("id");

            String status =
                    req.getParameter("status");

            if (id == null || status == null) {

                ResponseUtil.error(
                        resp,
                        "Purchase Order ID and Status are required");

                return;

            }

            ResponseUtil.success(
                    resp,
                    "Purchase Order Updated",
                    purchaseOrderService
                            .updatePurchaseOrderStatus(

                                    Long.parseLong(id),

                                    PurchaseOrderStatus.valueOf(status)

                            ));

        } catch (Exception e) {

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            ResponseUtil.error(resp,
                    e.getMessage());

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
                        "Purchase Order ID required");

                return;

            }

            purchaseOrderService.deletePurchaseOrder(
                    Long.parseLong(id));

            ResponseUtil.success(
                    resp,
                    "Purchase Order deleted successfully",
                    null);

        } catch (Exception e) {

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            ResponseUtil.error(
                    resp,
                    e.getMessage());

        }

    }

}