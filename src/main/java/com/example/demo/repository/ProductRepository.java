package com.example.demo.repository;

import com.example.demo.model.Product;
import com.example.demo.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // SELECT * FROM product
    @Query("SELECT p FROM Product p")
    List<Product> selectAll();

    // SELECT * FROM product LIMIT ? OFFSET ?
    @Query("SELECT p FROM Product p")
    Page<Product> selectAll(Pageable pageable);

    // SELECT * FROM product WHERE id = ?
    @Query("SELECT p FROM Product p WHERE p.id = :id")
    Optional<Product> selectById(@Param("id") Long id);

    // SELECT * FROM product WHERE name = ?
    @Query("SELECT p FROM Product p WHERE p.name = :name")
    List<Product> selectByName(@Param("name") String name);

    // SELECT * FROM product WHERE price > ?
    @Query("SELECT p FROM Product p WHERE p.price > :price")
    List<Product> selectByPriceGreaterThan(@Param("price") double price);

    // SELECT * FROM product WHERE LOWER(name) LIKE '%?%'
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Product> searchByName(@Param("keyword") String keyword, Pageable pageable);

    // SELECT * FROM product ORDER BY price ASC
    @Query("SELECT p FROM Product p ORDER BY p.price ASC")
    List<Product> orderByPriceAsc();

    // SELECT * FROM product ORDER BY price DESC
    @Query("SELECT p FROM Product p ORDER BY p.price DESC")
    List<Product> orderByPriceDesc();

    // SELECT MIN(price) FROM product
    @Query("SELECT MIN(p.price) FROM Product p")
    Double minPrice();

    // SELECT MAX(price) FROM product
    @Query("SELECT MAX(p.price) FROM Product p")
    Double maxPrice();

    // SELECT category.name, COUNT(product.id) FROM product JOIN category GROUP BY category.name
    @Query("SELECT p.category.name, COUNT(p) FROM Product p GROUP BY p.category.name")
    List<Object[]> groupByCategory();

    // SELECT * FROM product INNER JOIN category WHERE category.name = ?
    @Query("SELECT p FROM Product p JOIN p.category c WHERE c.name = :categoryName")
    List<Product> innerJoinCategory(@Param("categoryName") String categoryName);

    // SELECT * FROM product LEFT JOIN product_detail
    @Query("SELECT p FROM Product p LEFT JOIN p.productDetail d")
    List<Product> leftJoinDetails();

    // SELECT * FROM product LEFT JOIN product_detail WHERE manufacturer = ?
    @Query("SELECT p FROM Product p LEFT JOIN p.productDetail d WHERE d.manufacturer = :manufacturer")
    List<Product> leftJoinWithCondition(@Param("manufacturer") String manufacturer);

    // SELECT * FROM product CROSS JOIN category
    @Query("SELECT p, c FROM Product p, Category c")
    List<Object[]> crossJoin();

    // UPDATE product SET price = ? WHERE id = ?
    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.price = :price WHERE p.id = :id")
    int updatePrice(@Param("id") Long id, @Param("price") double price);

    // DELETE FROM product WHERE price < ?
    @Transactional
    @Modifying
    @Query("DELETE FROM Product p WHERE p.price < :price")
    int deleteBelowPrice(@Param("price") double price);

    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
