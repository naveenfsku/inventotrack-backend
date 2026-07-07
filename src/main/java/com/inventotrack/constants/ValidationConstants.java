package com.inventotrack.constants;

public final class ValidationConstants {

    private ValidationConstants() {
    }

    public static final String PRODUCT_NAME_REQUIRED =
            "Product name is required.";

    public static final String PRODUCT_PRICE_INVALID =
            "Product price must be greater than zero.";

    public static final String STOCK_INVALID =
            "Invalid stock quantity.";

    public static final String SUPPLIER_NAME_REQUIRED =
            "Supplier name is required.";

    public static final String SUPPLIER_EMAIL_REQUIRED =
            "Supplier email is required.";

    public static final String SUPPLIER_PHONE_REQUIRED =
            "Supplier phone is required.";

    public static final String USER_REQUIRED =
            "User is required.";

    public static final String ORDER_ITEMS_REQUIRED =
            "Order must contain at least one item.";

    public static final String PURCHASE_ITEMS_REQUIRED =
            "Purchase Order must contain at least one item.";

}