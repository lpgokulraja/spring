package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductQueryService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-queries")
public class ProductQueryController {

    private final ProductQueryService service;

    public ProductQueryController(ProductQueryService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Product> getAllUsingJPQL() {
        return service.getAllProducts();
    }

    @GetMapping("/page")
    public Page<Product> getAllPaged(
            @RequestParam int page,
            @RequestParam int size
    ) {
        return service.getAllProductsPaged(page, size);
    }

    @GetMapping("/search")
    public Page<Product> searchByName(
            @RequestParam String keyword,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return service.searchByName(keyword, page, size);
    }

    @GetMapping("/min-price")
    public Double minPrice() {
        return service.getMinPrice();
    }

    @GetMapping("/max-price")
    public Double maxPrice() {
        return service.getMaxPrice();
    }

    @GetMapping("/group-by-category")
    public List<Object[]> groupByCategory() {
        return service.groupByCategory();
    }

    @GetMapping("/category")
    public List<Product> byCategory(@RequestParam String name) {
        return service.getByCategoryName(name);
    }

    @PutMapping("/update-price")
    public int updatePrice(
            @RequestParam Long id,
            @RequestParam double price
    ) {
        return service.updatePrice(id, price);
    }

    @DeleteMapping("/delete-below")
    public int deleteBelow(@RequestParam double price) {
        return service.deleteBelowPrice(price);
    }
}
