package com.pedrojvdv.jdbc.dao;

import com.pedrojvdv.jdbc.model.Discount;
import com.pedrojvdv.jdbc.model.DiscountType;
import com.pedrojvdv.jdbc.queries.DiscountQueries;
import com.pedrojvdv.jdbc.queries.DiscountCrudQueries;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiscountDao {

    private final Connection connection;

    public DiscountDao(Connection connection) {
        this.connection = connection;
    }

    public void createDiscount(Discount discount)throws SQLException{
        String sql = DiscountCrudQueries.CREATE_DISCOUNT;

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setLong(1, discount.getProductId());
            stmt.setBigDecimal(2, discount.getPercentage());
            stmt.setBoolean(3, discount.isActive());
            stmt.setDate(4, Date.valueOf(discount.getStartDate()));
            stmt.setDate(5, Date.valueOf(discount.getEndDate()));
            stmt.executeUpdate();
        }
    }
    public void updateDiscount(Discount discount) throws SQLException{
        String sql = DiscountCrudQueries.UPDATE_DISCOUNT;

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setLong(1, discount.getProductId());
            stmt.setBigDecimal(2, discount.getPercentage());
            stmt.setBoolean(3, discount.isActive());
            stmt.setDate(4, Date.valueOf(discount.getStartDate()));
            stmt.setDate(5, Date.valueOf(discount.getEndDate()));
            stmt.setLong(6, discount.getId());
            stmt.executeUpdate();
        }
    }
    public void updateEndDate(Discount discount)throws SQLException{
        String sql = DiscountCrudQueries.UPDATE_END_DATE;

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setDate(1, Date.valueOf(discount.getEndDate()));
            stmt.setLong(2, discount.getId());
            stmt.executeUpdate();
        }
    }
    public void deleteDiscount(Long id)throws SQLException{
        String sql = DiscountCrudQueries.DELETE_DISCOUNT;

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
    public void softDeleteDiscount(Long id)throws SQLException{
        String sql = DiscountCrudQueries.SOFT_DELETE_DISCOUNT;

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
    public void deactivateExpiredDiscount() throws SQLException {
        String sql = DiscountQueries.DEACTIVATE_EXPIRED_DISCOUNT;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
    }
    public List<Discount> findActiveDiscounts() throws SQLException {
        List<Discount> activeDiscounts = new ArrayList<>();

        String sql = DiscountQueries.FIND_ALL_ACTIVE_DISCOUNTS;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Discount discount = mapResultToDiscount(rs);
                activeDiscounts.add(discount);
            }
            return activeDiscounts;
        }
    }
    public List<Discount> findFlashSalesAdmin() throws SQLException {
        List<Discount> adminFlashSale = new ArrayList<>();

        String sql = DiscountQueries.FIND_FLASH_SALES_ADMIN;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Discount discount = mapResultToDiscount(rs);
                adminFlashSale.add(discount);
            }
            return adminFlashSale;
        }
    }
    public List<Discount> findFlashSaleCustomer()throws SQLException{
        List<Discount> customerFlashSale = new ArrayList<>();

        String sql = DiscountQueries.FIND_FLASH_SALES_CUSTOMER;

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Discount discount = mapResultToDiscount(rs);
                customerFlashSale.add(discount);
            }
            return customerFlashSale;
        }
    }
    public List<Discount> findActiveDiscountById(Long id)throws SQLException{
        List<Discount> activeDiscounts = new ArrayList<>();

        String sql = DiscountQueries.FIND_ACTIVE_DISCOUNTS_BY_ID;

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setLong(1,id);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Discount discount = mapResultToDiscount(rs);
                activeDiscounts.add(discount);
            }
            return activeDiscounts;
        }
    }
    public List<Discount> findExpiredDiscounts()throws SQLException{
        List<Discount> expiredDiscounts = new ArrayList<>();

        String sql = DiscountQueries.FIND_EXPIRED_DISCOUNTS;

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Discount discount = mapResultToDiscount(rs);
                expiredDiscounts.add(discount);
            }
            return expiredDiscounts;
        }
    }
    private Discount mapResultToDiscount(ResultSet rs) throws SQLException {
        Discount discount = new Discount();

        if (hasColumn(rs, "id")) {
            discount.setId(rs.getLong("id"));
        }
        if (hasColumn(rs, "product_id")) {
            discount.setProductId(rs.getLong("product_id"));
        }
        if (hasColumn(rs, "percentage")) {
            discount.setPercentage(rs.getBigDecimal("percentage"));
        }
        if (hasColumn(rs, "type")) {
            discount.setType(DiscountType.valueOf(rs.getString("type")));
        }
        if (hasColumn(rs, "start_date")) {
            discount.setStartDate(rs.getDate("start_date").toLocalDate());
        }
        if (hasColumn(rs, "end_date")) {
            discount.setEndDate(rs.getDate("end_date").toLocalDate());
        }
        if (hasColumn(rs, "duration_hours")) {
            discount.setDurationHours(rs.getInt("duration_hours"));
        }
        if (hasColumn(rs, "active")) {
            discount.setActive(rs.getBoolean("active"));
        }
        if (hasColumn(rs, "product_name")) {
            discount.setProductName(rs.getString("product_name"));
        }
        if (hasColumn(rs, "product_price")) {
            discount.setProductPrice(rs.getBigDecimal("product_price"));
        }
        return discount;
    }
    private boolean hasColumn(ResultSet rs, String columnName) {
        try {
            rs.findColumn(columnName);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
