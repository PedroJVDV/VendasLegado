package com.pedrojvdv.jdbc.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

public class Product {

    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private String category;
    private int stock;
    private Boolean available;
    private LocalDateTime createdAt;


    private BigDecimal discountPercentage;
    private BigDecimal finalPrice;

    public Product() {
    }

    public Product(String name, BigDecimal price, int stock, Boolean available) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.available = true;
    }

    // Getters

    public Long getId() {return id;}

    public LocalDateTime getCreatedAt() {return createdAt;}

    public Boolean getAvailable() {return available;}

    public int getStock() {return stock;}

    public String getCategory() {return category;}

    public String getDescription() {return description;}

    public BigDecimal getPrice() {return price;}

    public String getName() {return name;}

    public BigDecimal getDiscountPercentage() {return discountPercentage;}

    public BigDecimal getFinalPrice() {return finalPrice;}


    // Setters

    public void setId(Long id) {this.id = id;}

    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}

    public void setAvailable(Boolean available) {this.available = available;}

    public void setStock(int stock) {this.stock = stock;}

    public void setCategory(String category) {this.category = category;}

    public void setDescription(String description) {this.description = description;}

    public void setPrice(BigDecimal price) {this.price = price;}

    public void setName(String name) {this.name = name;}

    public void setDiscountPercentage(BigDecimal discountPercentage) {this.discountPercentage = discountPercentage;}

    public void setFinalPrice(BigDecimal finalPrice) {this.finalPrice = finalPrice;}
}
