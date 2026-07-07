package com.inventotrack.controller;

import com.inventotrack.dto.InventoryTransactionDTO;
import com.inventotrack.enums.InventoryTransactionType;
import com.inventotrack.factory.ServiceFactory;
import com.inventotrack.service.InventoryTransactionService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/inventory-transactions")
public class InventoryTransactionServlet extends BaseServlet {

    private final InventoryTransactionService service =
            ServiceFactory.getInventoryTransactionService();

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws IOException {

        try {

            InventoryTransactionDTO dto =
                    readJson(req, InventoryTransactionDTO.class);

            InventoryTransactionDTO saved =
                    service.createTransaction(dto);

            resp.setStatus(HttpServletResponse.SC_CREATED);

            writeSuccess(
                    resp,
                    "Inventory transaction created successfully.",
                    saved);

        } catch (Exception e) {

            handleException(resp, e);

        }

    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws IOException {

        try {

            String id = req.getParameter("id");
            String productId = req.getParameter("productId");
            String type = req.getParameter("type");
            String referenceType = req.getParameter("referenceType");
            String referenceId = req.getParameter("referenceId");

            if (id != null) {

                writeSuccess(
                        resp,
                        "Inventory transaction fetched successfully.",
                        service.getTransactionById(
                                Long.parseLong(id)));

                return;
            }

            if (productId != null) {

                writeSuccess(
                        resp,
                        "Product inventory history fetched successfully.",
                        service.getTransactionsByProduct(
                                Long.parseLong(productId)));

                return;
            }

            if (type != null) {

                writeSuccess(
                        resp,
                        "Transactions fetched successfully.",
                        service.getTransactionsByType(
                                InventoryTransactionType.valueOf(type)));

                return;
            }

            if (referenceType != null &&
                    referenceId != null) {

                writeSuccess(
                        resp,
                        "Reference transactions fetched successfully.",
                        service.getTransactionsByReference(
                                referenceType,
                                Long.parseLong(referenceId)));

                return;
            }

            writeSuccess(
                    resp,
                    "Inventory transactions fetched successfully.",
                    service.getAllTransactions());

        } catch (Exception e) {

            handleException(resp, e);

        }

    }

}