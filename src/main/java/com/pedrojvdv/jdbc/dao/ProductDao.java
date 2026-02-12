package com.pedrojvdv.jdbc.dao;

import com.pedrojvdv.jdbc.config.ConnectionFactory;
import com.pedrojvdv.jdbc.model.Product;
import com.pedrojvdv.jdbc.queries.ProductQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDao {

    private Connection connection;

    public Product findById (Long id) throws SQLException {

        String sql = ProductQueries.FIND_BY_ID;

        try (PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){

                Product product = new Product();
                product.setId(rs.getLong("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setStock(rs.getInt("stock"));
                product.setCategory(rs.getString("category"));
                product.setAvailable(rs.getBoolean("available"));

                return product;
            }

        }catch (Exception e){
            System.out.println("Ocorreu um erro inesperado, Tente novamente mais tarde!");
        }
        return null;
    }



}
