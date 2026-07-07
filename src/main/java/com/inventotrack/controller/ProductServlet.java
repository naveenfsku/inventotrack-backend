package com.inventotrack.controller;

import com.inventotrack.dto.ProductDTO;
import com.inventotrack.service.ProductService;
import com.inventotrack.factory.ServiceFactory;
import com.inventotrack.util.ExceptionHandlerUtil;
import com.inventotrack.util.JsonUtil;
import com.inventotrack.util.ResponseUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/products")
public class ProductServlet extends HttpServlet {

    private final ProductService productService =
            ServiceFactory.getProductService();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws IOException {

        try {

            String id = req.getParameter("id");
            String search = req.getParameter("search");
            String lowStock = req.getParameter("lowStock");

            if (id != null) {

                ResponseUtil.success(
                        resp,
                        "Product fetched successfully",
                        productService.getProductById(Long.parseLong(id))
                );

                return;
            }

            if (search != null) {

                ResponseUtil.success(
                        resp,
                        "Search completed",
                        productService.searchProducts(search)
                );

                return;
            }

            if ("true".equalsIgnoreCase(lowStock)) {

                ResponseUtil.success(
                        resp,
                        "Low stock products",
                        productService.getLowStockProducts()
                );

                return;
            }

            ResponseUtil.success(
                    resp,
                    "Products fetched successfully",
                    productService.getAllProducts()
            );

        } catch (Exception e) {

            ExceptionHandlerUtil.handle(e, resp);

            ResponseUtil.error(resp, e.getMessage());

        }

    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws IOException {

        try {

            ProductDTO dto =
                    JsonUtil.readJson(req, ProductDTO.class);

            ProductDTO saved =
                    productService.createProduct(dto);

            resp.setStatus(HttpServletResponse.SC_CREATED);

            ResponseUtil.success(
                    resp,
                    "Product created successfully",
                    saved
            );

        } catch (Exception e) {

            ExceptionHandlerUtil.handle(e, resp);

            ResponseUtil.error(resp, e.getMessage());

        }

    }
    @Override
    protected void doPut(HttpServletRequest req,
                         HttpServletResponse resp)
            throws IOException {

        try {

            String id = req.getParameter("id");

            if (id == null) {

                ResponseUtil.error(resp, "Product ID is required");

                return;
            }

            ProductDTO dto =
                    JsonUtil.readJson(req, ProductDTO.class);

            ProductDTO updated =
                    productService.updateProduct(
                            Long.parseLong(id),
                            dto
                    );

            ResponseUtil.success(
                    resp,
                    "Product updated successfully",
                    updated
            );

        } catch (Exception e) {

            ExceptionHandlerUtil.handle(e, resp);

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

                ResponseUtil.error(resp,
                        "Product ID is required");

                return;

            }

            productService.deleteProduct(
                    Long.parseLong(id));

            ResponseUtil.success(
                    resp,
                    "Product deleted successfully",
                    null
            );

        } catch (Exception e) {

            ExceptionHandlerUtil.handle(e, resp);

            ResponseUtil.error(resp,
                    e.getMessage());

        }

    }
}