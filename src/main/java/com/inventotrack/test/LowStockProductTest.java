package com.inventotrack.test;

import com.inventotrack.dto.ProductDTO;
import com.inventotrack.factory.ServiceFactory;
import com.inventotrack.service.ProductService;

import java.util.List;

public class LowStockProductTest {

    public static void main(String[] args) {

        ProductService service = ServiceFactory.getProductService();

        List<ProductDTO> products =
                service.getLowStockProducts();

        System.out.println("Low Stock Products");

        for (ProductDTO p : products) {

            System.out.println("------------------");
            System.out.println(p.getName());
            System.out.println("Stock : " + p.getStockQuantity());

        }

    }
}