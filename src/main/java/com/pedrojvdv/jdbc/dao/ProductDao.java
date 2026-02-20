package com.pedrojvdv.jdbc.dao;

import com.pedrojvdv.jdbc.model.Product;
import com.pedrojvdv.jdbc.queries.ProductQueries;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    private final Connection connection;

    public ProductDao(Connection connection) {
        this.connection = connection;
    }

    public Product findById(Long id) throws SQLException {

        String sql = ProductQueries.FIND_BY_ID;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToProduct(rs);
            }
        }
        return null;
    }

    public List<Product> findAll() throws SQLException {

        List<Product> productList = new ArrayList<>();

        String sql = ProductQueries.FIND_ALL_PRODUCTS;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Product product = mapResultSetToProduct(rs);
                productList.add(product);
            }
            return productList;
        }
    }

    public List<Product> findByCategory(String category) throws SQLException {

        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Categoria não pode estar vazia!!");
        }
        List<Product> productsCategoryList = new ArrayList<>();
        String sql = ProductQueries.FIND_BY_CATEGORY;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, category);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Product product = mapResultSetToProduct(rs);
                productsCategoryList.add(product);
            }
            return productsCategoryList;
        }
    }

    public List<Product> findProductWithDiscount() throws SQLException {

        List<Product> discountProducts = new ArrayList<>();
        String sql = ProductQueries.FIND_DISCOUNT_PRODUCTS;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                discountProducts.add(mapResultSetToProduct(rs));
            }
            return discountProducts;
        }

    }

    private Product mapResultSetToProduct(ResultSet rs) throws SQLException {
        Product product = new Product();

        // DADOS BÁSICOS
        if (hasColumn(rs, "id")) {
            product.setId(rs.getLong("id"));
        }
        if (hasColumn(rs, "name")) {
            product.setName(rs.getString("name"));
        }
        if (hasColumn(rs, "price")) {
            product.setPrice(rs.getBigDecimal("price"));
        }

        // CATEGORIZAÇÃO DO PRODUTO
        if (hasColumn(rs, "category")) {
            product.setCategory(rs.getString("category"));
        }
        if (hasColumn(rs, "description")) {
            product.setDescription(rs.getString("description"));
        }

        // AUDITORIA
        if (hasColumn(rs, "created_at")) {
            product.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        }

        // ESTOQUE E DISPONIBILIDADE
        if (hasColumn(rs, "stock")) {
            product.setStock(rs.getInt("stock"));
        }
        if (hasColumn(rs, "available")) {
            product.setAvailable(rs.getBoolean("available"));
        }

        // DESCONTO - CÁLCULOS REALIZADOS
        if (hasColumn(rs, "discount_percentage")) {
            product.setDiscountPercentage(rs.getBigDecimal("discount_percentage"));
        }
        if (hasColumn(rs, "final_price")) {
            product.setFinalPrice(rs.getBigDecimal("final_price"));
        }
        return product;
    }

    /* verifica se existe um resultSet
     *  Mapeia conforme as queries pré-estabelecidas
     *  SqlException é evitado, uma vez que já executa as necessárias*/

    //TODO: implementar comentários mais complexos a respeito disso e outras funções

    private boolean hasColumn(ResultSet rs, String columnName) {
        try {
            rs.findColumn(columnName);
            return true; // achou a coluna
        } catch (SQLException e) {
            return false; // não achou a coluna
        }
    }

}
