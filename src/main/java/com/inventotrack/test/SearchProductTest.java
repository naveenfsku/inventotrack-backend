package com.inventotrack.test;

import com.inventotrack.dto.ProductDTO;
import com.inventotrack.factory.ServiceFactory;
import com.inventotrack.service.ProductService;

import java.util.List;

public class SearchProductTest {

    public static void main(String[] args) {

        ProductService service = ServiceFactory.getProductService();

        List<ProductDTO> products =
                service.searchProducts("Laptop");

        System.out.println("Search Results");

        for (ProductDTO p : products) {

            System.out.println("------------------");
            System.out.println(p.getId());
            System.out.println(p.getName());
            System.out.println(p.getCategory());

        }

    }
}