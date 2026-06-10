package com.pedrojvdv.jdbc.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Cart {

    private Long id;
    private Long productId;
    private Long userId;
    private int quantity;
    private LocalDateTime addedAt;
    private String productName;
    private BigDecimal originalPrice;
    private BigDecimal productDiscount;
    private BigDecimal finalPrice;
    private String discountType;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public BigDecimal getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(BigDecimal productDiscount) {
        this.productDiscount = productDiscount;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Cart() {
    }

    public Cart(Long productId, Long userId, int quantity) {
        this.productId = productId;
        this.userId = userId;
        this.quantity = quantity;
        this.addedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }


    public String AdmintoString() {
        return "cartItem{" +
                "id=" + id +
                ", productId=" + productId +
                ", userId=" + userId +
                ", quantity=" + quantity +
                ", addedAt=" + addedAt +
                ", productName='" + productName + '\'' +
                ", originalPrice=" + originalPrice +
                ", productDiscount=" + productDiscount +
                ", finalPrice=" + finalPrice +
                ", discountType='" + discountType + '\'' +
                '}';
    }

    public String UserToString() {
        return "cartItem{" +
                ", quantity=" + quantity +
                ", productName='" + productName + '\'' +
                ", originalPrice=" + originalPrice +
                ", productDiscount=" + productDiscount +
                ", finalPrice=" + finalPrice +
                ", discountType='" + discountType + '\'' +
                '}';
    }
}



