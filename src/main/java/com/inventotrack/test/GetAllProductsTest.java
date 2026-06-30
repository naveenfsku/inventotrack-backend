package com.inventotrack.test;

import com.inventotrack.dto.ProductDTO;
import com.inventotrack.factory.ServiceFactory;
import com.inventotrack.service.ProductService;

import java.util.List;

public class GetAllProductsTest {

    public static void main(String[] args) {

        ProductService service = ServiceFactory.getProductService();

        List<ProductDTO> products = service.getAllProducts();

        System.out.println("Total Products : " + products.size());

        for (ProductDTO p : products) {

            System.out.println("-----------------------------");
            System.out.println("ID       : " + p.getId());
            System.out.println("Name     : " + p.getName());
            System.out.println("Category : " + p.getCategory());
            System.out.println("Price    : " + p.getPrice());
            System.out.println("Stock    : " + p.getStockQuantity());

        }
    }
}