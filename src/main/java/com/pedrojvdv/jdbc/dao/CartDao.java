package com.pedrojvdv.jdbc.dao;

import com.pedrojvdv.jdbc.config.ConnectionFactory;
import com.pedrojvdv.jdbc.model.Cart;
import com.pedrojvdv.jdbc.queries.cart.CartQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CartDao implements AutoCloseable {

    private final Connection connection;

    public CartDao() {
        this.connection = ConnectionFactory.getConnection();
    }

    public void createCart(Cart cart) throws SQLException {
        String sql = CartQueries.CREATE;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, cart.getProductId());
            stmt.setLong(2, cart.getUserId());
            stmt.setInt(3, cart.getQuantity());
            stmt.setObject(4, cart.getAddedAt());
            stmt.executeUpdate();
        }
    }

    public void updateCartQuantity(Cart cart) throws SQLException {
        String sql = CartQueries.UPDATE_QUANTITY;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, cart.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteCart(Long id) throws SQLException {
        String sql = CartQueries.DELETE;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public void deleteCartByUser(Long userId)throws SQLException{
        String sql = CartQueries.DELETE;

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setLong(1, userId);
            stmt.executeUpdate();
        }
    }

    public void softDeleteCart(Long userId) throws SQLException {
        String sql = CartQueries.SOFT_DELETE;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, userId);
            stmt.executeUpdate();
        }
    }

    public List<Cart> findCartProducts(Long userId)throws SQLException{
        List<Cart> cartList = new ArrayList<>();
        String sql = CartQueries.FIND_CART_PRODUCTS;

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                cartList.add(mapToResultSet(rs));
            }
        }
        return cartList;
    }

    public List<Cart> findCartById(Long id) throws SQLException {
        List<Cart> cartList = new ArrayList<>();
        String sql = CartQueries.FIND_CART_USERID;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cartList.add(mapToResultSet(rs));
            }
        }
        return cartList;
    }

    private Cart mapToResultSet(ResultSet rs) throws SQLException {
        Cart cart = new Cart();
        cart.setId(rs.getLong("id"));
        cart.setProductId(rs.getLong("product_id"));
        cart.setUserId(rs.getLong("user_id"));
        cart.setQuantity(rs.getInt("quantity"));
        cart.setAddedAt(rs.getObject("added_at", LocalDateTime.class));
        cart.setQuantity(rs.getInt("quantity"));
        cart.setProductName(rs.getString("product_name"));
        cart.setOriginalPrice(rs.getBigDecimal("original_price"));
        cart.setProductDiscount(rs.getBigDecimal("product_discount"));
        cart.setFinalPrice(rs.getBigDecimal("final_price"));
        cart.setDiscountType(rs.getString("discount_type"));
        return cart;
    }

    @Override
    public void close() throws Exception {
        if (connection != null && connection.isClosed()) {
            connection.close();
        }
    }
}
