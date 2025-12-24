package com.example.demo.service.impl;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductQueryService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductQueryServiceImpl implements ProductQueryService {

    private final ProductRepository repository;

    public ProductQueryServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.selectAll();
    }

    @Override
    public Page<Product> getAllProductsPaged(int page, int size) {
        return repository.selectAll(PageRequest.of(page, size));
    }

    @Override
    public Page<Product> searchByName(String keyword, int page, int size) {
        return repository.searchByName(keyword, PageRequest.of(page, size));
    }

    @Override
    public Double getMinPrice() {
        return repository.minPrice();
    }

    @Override
    public Double getMaxPrice() {
        return repository.maxPrice();
    }

    @Override
    public List<Object[]> groupByCategory() {
        return repository.groupByCategory();
    }

    @Override
    public List<Product> getByCategoryName(String categoryName) {
        return repository.innerJoinCategory(categoryName);
    }

    @Override
    public int updatePrice(Long id, double price) {
        return repository.updatePrice(id, price);
    }

    @Override
    public int deleteBelowPrice(double price) {
        return repository.deleteBelowPrice(price);
    }
}
