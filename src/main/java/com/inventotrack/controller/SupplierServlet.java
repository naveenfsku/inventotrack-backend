package com.inventotrack.controller;

import com.inventotrack.dto.SupplierDTO;
import com.inventotrack.factory.ServiceFactory;
import com.inventotrack.service.SupplierService;
import com.inventotrack.util.ExceptionHandlerUtil;
import com.inventotrack.util.JsonUtil;
import com.inventotrack.util.ResponseUtil;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/suppliers")
public class SupplierServlet extends HttpServlet {

    private final SupplierService supplierService =
            ServiceFactory.getSupplierService();

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws IOException {

        try {

            SupplierDTO dto =
                    JsonUtil.readJson(req, SupplierDTO.class);

            SupplierDTO saved =
                    supplierService.createSupplier(dto);

            resp.setStatus(HttpServletResponse.SC_CREATED);

            ResponseUtil.success(
                    resp,
                    "Supplier created successfully",
                    saved);

        } catch (Exception e) {

            ExceptionHandlerUtil.handle(e, resp);

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
            String search = req.getParameter("search");

            if (id != null) {

                ResponseUtil.success(
                        resp,
                        "Supplier fetched successfully",
                        supplierService.getSupplierById(
                                Long.parseLong(id)));

                return;
            }

            if (search != null) {

                ResponseUtil.success(
                        resp,
                        "Search completed",
                        supplierService.searchSuppliers(search));

                return;
            }

            ResponseUtil.success(
                    resp,
                    "Suppliers fetched successfully",
                    supplierService.getAllSuppliers());

        } catch (Exception e) {

            ExceptionHandlerUtil.handle(e, resp);

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

            if (id == null) {

                ResponseUtil.error(
                        resp,
                        "Supplier ID is required");

                return;
            }

            SupplierDTO dto =
                    JsonUtil.readJson(req, SupplierDTO.class);

            SupplierDTO updated =
                    supplierService.updateSupplier(
                            Long.parseLong(id),
                            dto);

            ResponseUtil.success(
                    resp,
                    "Supplier updated successfully",
                    updated);

        } catch (Exception e) {

            ExceptionHandlerUtil.handle(e, resp);

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
                        "Supplier ID is required");

                return;
            }

            supplierService.deleteSupplier(
                    Long.parseLong(id));

            ResponseUtil.success(
                    resp,
                    "Supplier deleted successfully",
                    null);

        } catch (Exception e) {

            ExceptionHandlerUtil.handle(e, resp);

            ResponseUtil.error(resp,
                    e.getMessage());

        }

    }

}