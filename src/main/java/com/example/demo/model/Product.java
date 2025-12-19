package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "product") // optional but good practice
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    // Mandatory default constructor
    public Product() {}

    // Optional convenience constructor
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
