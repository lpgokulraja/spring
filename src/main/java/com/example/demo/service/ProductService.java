package com.example.demo.service;
import org.springframework.data.domain.Page;

import com.example.demo.model.Product;
import java.util.List;

public interface ProductService {

    Product createProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);

    Page<Product> getProductsWithPagination(int page, int size, String sortBy);

    Page<Product> getProducts(
            int page,
            int size,
            String sortBy,
            String direction,
            String search
    );
}
