package com.inventotrack.test;

import com.inventotrack.dto.ProductDTO;
import com.inventotrack.factory.ServiceFactory;
import com.inventotrack.service.ProductService;

public class GetProductByIdTest {

    public static void main(String[] args) {

        ProductService service = ServiceFactory.getProductService();

        ProductDTO product = service.getProductById(2L);

        System.out.println("------------");
        System.out.println("ID       : " + product.getId());
        System.out.println("Name     : " + product.getName());
        System.out.println("Category : " + product.getCategory());
        System.out.println("Price    : " + product.getPrice());
        System.out.println("Stock    : " + product.getStockQuantity());
        System.out.println("------------");
    }
}