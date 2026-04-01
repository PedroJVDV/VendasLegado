package com.pedrojvdv.jdbc.dao;

import com.pedrojvdv.jdbc.config.ConnectionFactory;
import com.pedrojvdv.jdbc.model.Sale;
import com.pedrojvdv.jdbc.queries.sale.SalesQueries;

import javax.xml.transform.Result;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SalesDao {

    private final Connection connection;

    public SalesDao() {
        this.connection = ConnectionFactory.getConnection();
    }

    public void createSale(Sale sale) throws SQLException {
        String sql = SalesQueries.CREATE;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            mapToStatement(sale, stmt);
            stmt.executeUpdate();
        }
    }

    public void updateSale(Sale sale) throws SQLException {
        String sql = SalesQueries.UPDATE;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            mapToStatement(sale, stmt);
            stmt.setLong(7, sale.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteSale(Long id) throws SQLException {
        String sql = SalesQueries.DELETE;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Sale> findAll() throws SQLException {
        List<Sale> saleList = new ArrayList<>();
        String sql = SalesQueries.FIND_ALL;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                saleList.add(mapToResultSale(rs));
            }
        }
        return saleList;
    }

    public List<Sale> findByQuantityRange(int min, int max) throws SQLException {
        List<Sale> saleList = new ArrayList<>();
        String sql = SalesQueries.FIND_BY_QUANTITY_RANGE;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, min);
            stmt.setInt(2, max);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                saleList.add(mapToResultSale(rs));
            }
        }
        return saleList;
    }

    public List<Sale> findByTotalPriceRange(BigDecimal min, BigDecimal max) throws SQLException {
        List<Sale> saleList = new ArrayList<>();
        String sql = SalesQueries.FIND_BY_TOTAL_PRICE_RANGE;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setBigDecimal(1, min);
            stmt.setBigDecimal(2, max);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                saleList.add(mapToResultSale(rs));
            }
        }
        return saleList;
    }

    public List<Sale> findByDateRange(LocalDateTime first, LocalDateTime last) throws SQLException {
        List<Sale> saleList = new ArrayList<>();
        String sql = SalesQueries.FIND_BY_DATE_RANGE;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, first);
            stmt.setObject(2, last);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                saleList.add(mapToResultSale(rs));
            }
        }
        return saleList;
    }

    public List<Sale> findByOrderId(Sale sale) throws SQLException {
        List<Sale> saleList = new ArrayList<>();
        String sql = SalesQueries.FIND_BY_ORDER_ID;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, sale.getOrderId());
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                saleList.add(mapToResultSale(rs));
            }
        }
        return saleList;
    }

    public List<Sale> findByProductId(Sale sale) throws SQLException {
        List<Sale> saleList = new ArrayList<>();
        String sql = SalesQueries.FIND_BY_ORDER_ID;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, sale.getProductId());
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                saleList.add(mapToResultSale(rs));
            }
        }
        return saleList;
    }

    public List<Sale> findById(Long id) throws SQLException{
        List<Sale> saleList = new ArrayList<>();
        String sql = SalesQueries.FIND_BY_ID;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                saleList.add(mapToResultSale(rs));
            }
        }
        return saleList;

    }

    private void mapToStatement(Sale sale, PreparedStatement stmt) throws SQLException {
        stmt.setLong(1, sale.getOrderId());
        stmt.setLong(2, sale.getProductId());
        stmt.setLong(3, sale.getDiscountId());
        stmt.setInt(4, sale.getQuantity());
        stmt.setBigDecimal(5, sale.getTotalPrice());
        stmt.setObject(6, sale.getSaleDate());
    }

    private Sale mapToResultSale(ResultSet rs) throws SQLException {
        Sale sale = new Sale();
        sale.setId(rs.getLong("id"));
        sale.setDiscountId(rs.getLong("discount_id"));
        sale.setOrderId(rs.getLong("order_id"));
        sale.setProductId(rs.getLong("product_id"));
        sale.setQuantity(rs.getInt("quantity"));
        sale.setTotalPrice(rs.getBigDecimal("total_price"));
        sale.setSaleDate(rs.getObject("sale_date", LocalDateTime.class));
        return sale;
    }

}
