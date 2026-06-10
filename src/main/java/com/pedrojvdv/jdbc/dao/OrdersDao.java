package com.pedrojvdv.jdbc.dao;

import com.pedrojvdv.jdbc.config.ConnectionFactory;
import com.pedrojvdv.jdbc.model.Order;
import com.pedrojvdv.jdbc.model.enums.Status;
import com.pedrojvdv.jdbc.queries.order.OrderQueries;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrdersDao implements AutoCloseable {

    private final Connection connection;

    public OrdersDao() {
        this.connection = ConnectionFactory.getConnection();
    }

    public void createOrder(Order order) throws SQLException {

        String sql = OrderQueries.CREATE_ORDER;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, order.getUserId());
            stmt.setBigDecimal(2, order.getTotalPrice());
            stmt.setString(3, order.getOrderStatus().name());
            stmt.setObject(4, order.getCreatedAt());
            stmt.setObject(5, order.getUpdatedAt());
            stmt.executeUpdate();
        }
    }

    public void deleteOrder(Long id) throws SQLException {
        String sql = OrderQueries.DELETE_ORDER;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public void updateOrder(Order order) throws SQLException {

        String sql = OrderQueries.UPDATE;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, order.getUserId());
            stmt.setBigDecimal(2, order.getTotalPrice());
            stmt.setString(3, order.getOrderStatus().name());
            stmt.setObject(4, order.getCreatedAt());
            stmt.setObject(5, order.getUpdatedAt());
            stmt.setLong(6, order.getId());
            stmt.executeUpdate();
        }
    }

    public List<Order> findById(Long id) throws SQLException {
        List<Order> orderList = new ArrayList<>();

        String sql = OrderQueries.FIND_BY_ID;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                orderList.add(mapResultSetOder(rs));
            }
        }
        return orderList;
    }

    public List<Order> findByStatus(Status orderStatus) throws SQLException {
        List<Order> orderList = new ArrayList<>();

        String sql = OrderQueries.FIND_BY_STATUS;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, String.valueOf(orderStatus));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                orderList.add(mapResultSetOder(rs));
            }
        }
        return orderList;
    }

    public List<Order> findByPriceRange(BigDecimal min, BigDecimal max) throws SQLException {
        List<Order> orderList = new ArrayList<>();

        String sql = OrderQueries.FIND_BY_PRICE_RANGE;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setBigDecimal(1, min);
            stmt.setBigDecimal(2, max);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                orderList.add(mapResultSetOder(rs));
            }
        }
        return orderList;
    }

    public List<Order> findOrderByUpdateRange(LocalDateTime min, LocalDateTime max) throws SQLException {
        List<Order> orderList = new ArrayList<>();

        String sql = OrderQueries.FIND_ORDER_BY_UPDATE_RANGE;
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setObject(1, min);
            stmt.setObject(2, max);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                orderList.add(mapResultSetOder(rs));
            }
        }
        return orderList;
    }

    public List<Order> findAllOrders()throws SQLException{
        List<Order> orderList = new ArrayList<>();

        String sql = OrderQueries.FIND_ALL;
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                orderList.add(mapResultSetOder(rs));
            }
        }
        return orderList;
    }

    private Order mapResultSetOder(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getLong("id"));
        order.setUserId(resultSet.getLong("user_id"));
        order.setTotalPrice(resultSet.getBigDecimal("total_price"));
        order.setOrderStatus(Status.valueOf(resultSet.getString("status")));
        order.setCreatedAt(resultSet.getObject("created_at", LocalDateTime.class));
        order.setUpdatedAt(resultSet.getObject("updated_at", LocalDateTime.class));
        return order;
    }

    @Override
    public void close() throws Exception {
        if (connection != null && connection.isClosed()){
            connection.close();
        }
    }
}
