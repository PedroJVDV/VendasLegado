package com.pedrojvdv.jdbc.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Discount {

    private Long id;
    private Long productId;
    private boolean active;
    private LocalDate start_date;
    private LocalDate end_date;
    private BigDecimal percentage;
    private DiscountType type;
    private Integer duration_hours;

    public Discount() {
    }

    public Discount(Long productId, BigDecimal percentage, DiscountType type) {
        this.productId = productId;
        this.percentage = percentage;
        this.type = type;
    }

    public Integer getDuration_hours() {
        return duration_hours;
    }

    public void setDuration_hours(Integer duration_hours) {
        this.duration_hours = duration_hours;
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

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
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
        if (end_date == null){
            return false;
        }
        return LocalDate.now().isAfter(end_date);
    }

    public boolean isScheduled(){
        if (start_date == null){
            return false;
        }
        return LocalDate.now().isBefore(start_date);
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
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", percentage=" + percentage +
                ", type=" + type +
                ", duration_hours=" + duration_hours +
                '}';
    }
}
