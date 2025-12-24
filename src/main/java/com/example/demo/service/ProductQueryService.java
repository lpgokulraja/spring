package com.example.demo.service;

import com.example.demo.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductQueryService {

    List<Product> getAllProducts();

    Page<Product> getAllProductsPaged(int page, int size);

    Page<Product> searchByName(String keyword, int page, int size);

    Double getMinPrice();

    Double getMaxPrice();

    List<Object[]> groupByCategory();

    List<Product> getByCategoryName(String categoryName);

    int updatePrice(Long id, double price);

    int deleteBelowPrice(double price);
}
