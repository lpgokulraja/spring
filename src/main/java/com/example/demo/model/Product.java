package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(mappedBy = "product")
    private ProductDetail productDetail;

    @ManyToMany
    @JoinTable(
        name = "product_tags",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;

    public Product() {}

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
        if (productDetail != null) {
            productDetail.setProduct(this);
        }
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}

@Entity
@Table(name = "product_detail")
class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String manufacturer;
    private int warrantyYears;

    @OneToOne
    @JoinColumn(name = "product_id", unique = true)
    private Product product;

    public ProductDetail() {}

    public ProductDetail(String manufacturer, int warrantyYears) {
        this.manufacturer = manufacturer;
        this.warrantyYears = warrantyYears;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

@Entity
@Table(name = "category")
class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Category() {}

    public Category(String name) {
        this.name = name;
    }
}

@Entity
@Table(name = "tag")
class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Tag() {}

    public Tag(String name) {
        this.name = name;
    }
}
