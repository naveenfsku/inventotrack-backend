package com.inventotrack.factory;

import com.inventotrack.dao.*;
import com.inventotrack.dao.impl.*;

public final class DAOFactory {

    private DAOFactory() {
    }

    // =========================
    // DAO Instances
    // =========================

    private static final UserDAO USER_DAO =
            new UserDAOImpl();

    private static final ProductDAO PRODUCT_DAO =
            new ProductDAOImpl();

    private static final CustomerOrderDAO ORDER_DAO =
            new CustomerOrderDAOImpl();

    private static final OrderItemDAO ORDER_ITEM_DAO =
            new OrderItemDAOImpl();

    private static final CustomerOrderDAO CUSTOMER_ORDER_DAO =
            new CustomerOrderDAOImpl();

    private static final SupplierDAO SUPPLIER_DAO =
            new SupplierDAOImpl();

    private static final PurchaseOrderDAO PURCHASE_ORDER_DAO =
            new PurchaseOrderDAOImpl();

    private static final DashboardDAO DASHBOARD_DAO =
            new DashboardDAOImpl();

    private static final ReportDAO REPORT_DAO =
            new ReportDAOImpl();

    private static final InventoryTransactionDAO INVENTORY_TRANSACTION_DAO =
            new InventoryTransactionDAOImpl();

    // =========================
    // DAO Getters
    // =========================

    public static UserDAO getUserDAO() {
        return USER_DAO;
    }

    public static ProductDAO getProductDAO() {
        return PRODUCT_DAO;
    }

    public static CustomerOrderDAO getOrderDAO() {
        return ORDER_DAO;
    }

    public static OrderItemDAO getOrderItemDAO() {
        return ORDER_ITEM_DAO;
    }

    public static CustomerOrderDAO getCustomerOrderDAO() {
        return CUSTOMER_ORDER_DAO;
    }

    public static SupplierDAO getSupplierDAO() {
        return SUPPLIER_DAO;
    }

    public static PurchaseOrderDAO getPurchaseOrderDAO() {
        return PURCHASE_ORDER_DAO;
    }

    public static DashboardDAO getDashboardDAO() {
        return DASHBOARD_DAO;
    }

    public static ReportDAO getReportDAO() {
        return REPORT_DAO;
    }

    public static InventoryTransactionDAO getInventoryTransactionDAO() {
        return INVENTORY_TRANSACTION_DAO;
    }

}