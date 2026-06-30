package com.inventotrack.test;

import com.inventotrack.factory.ServiceFactory;
import com.inventotrack.service.ProductService;

public class DeleteProductTest {

    public static void main(String[] args) {

        ProductService service = ServiceFactory.getProductService();

        service.deleteProduct(1L);

        System.out.println("Product deleted successfully.");

    }
}