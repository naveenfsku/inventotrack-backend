package com.inventotrack.factory;

import com.inventotrack.service.AuthService;
import com.inventotrack.service.OrderService;
import com.inventotrack.service.ProductService;
import com.inventotrack.service.impl.AuthServiceImpl;
import com.inventotrack.service.impl.OrderServiceImpl;
import com.inventotrack.service.impl.ProductServiceImpl;
import com.inventotrack.service.DashboardService;
import com.inventotrack.service.impl.DashboardServiceImpl;
import com.inventotrack.service.ReportService;
import com.inventotrack.service.impl.ReportServiceImpl;
import com.inventotrack.service.SupplierService;
import com.inventotrack.service.impl.SupplierServiceImpl;
import com.inventotrack.service.PurchaseOrderService;
import com.inventotrack.service.impl.PurchaseOrderServiceImpl;

public final class ServiceFactory {

    private static final AuthService AUTH_SERVICE =
            new AuthServiceImpl(DAOFactory.getUserDAO());

    private static final SupplierService SUPPLIER_SERVICE =
            new SupplierServiceImpl(
                    DAOFactory.getSupplierDAO());

    private static final ProductService PRODUCT_SERVICE =
            new ProductServiceImpl(DAOFactory.getProductDAO());
    private static final OrderService ORDER_SERVICE =
            new OrderServiceImpl(
                    DAOFactory.getCustomerOrderDAO(),
                    DAOFactory.getUserDAO(),
                    DAOFactory.getProductDAO()
            );
    private static final ReportService REPORT_SERVICE =
            new ReportServiceImpl(
                    DAOFactory.getReportDAO());

    private ServiceFactory() {
    }

    public static OrderService getOrderService() {
        return ORDER_SERVICE;
    }

    public static AuthService getAuthService() {
        return AUTH_SERVICE;
    }

    public static ProductService getProductService() {
        return PRODUCT_SERVICE;
    }

    private static final DashboardService DASHBOARD_SERVICE =
            new DashboardServiceImpl(
                    DAOFactory.getDashboardDAO());

    public static DashboardService getDashboardService() {
        return DASHBOARD_SERVICE;
    }

    public static ReportService getReportService() {
        return REPORT_SERVICE;
    }

    public static SupplierService getSupplierService() {
        return SUPPLIER_SERVICE;
    }

    private static final PurchaseOrderService PURCHASE_ORDER_SERVICE =
            new PurchaseOrderServiceImpl(
                    DAOFactory.getPurchaseOrderDAO(),
                    DAOFactory.getSupplierDAO(),
                    DAOFactory.getProductDAO());

    public static PurchaseOrderService getPurchaseOrderService() {
        return PURCHASE_ORDER_SERVICE;
    }
}
