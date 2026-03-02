package com.pedrojvdv.jdbc.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Discount {

    private Long id;
    private Long productId;
    private boolean active;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal percentage;
    private DiscountType type;
    private Integer durationHours;

    private String productName;
    private BigDecimal productPrice;

    public Discount() {
    }

    public Discount(Long productId, BigDecimal percentage, DiscountType type) {
        this.productId = productId;
        this.percentage = percentage;
        this.type = type;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(Integer durationHours) {
        this.durationHours = durationHours;
    }

    public DiscountType getType() {
        return type;
    }

    public void setType(DiscountType type) {
        this.type = type;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //TODO: comments @++

    public boolean isExpired(){
        if (endDate == null){
            return false;
        }
        return LocalDate.now().isAfter(endDate);
    }

    public boolean isScheduled(){
        if (startDate == null){
            return false;
        }
        return LocalDate.now().isBefore(startDate);
    }

    public boolean isActivated(){
        if (!active) return false;
        if (isScheduled()) return false;
        return !isExpired();
    }

    @Override
    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", productId=" + productId +
                ", active=" + active +
                ", start_date=" + startDate +
                ", end_date=" + endDate +
                ", percentage=" + percentage +
                ", type=" + type +
                ", duration_hours=" + durationHours +
                '}';
    }
}
