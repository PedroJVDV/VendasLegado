package com.pedrojvdv.jdbc.queries;

public class ProductCrudQueries {

    public static final String INSERT_PRODUCT = """
            INSERT INTO products(name, price, description, category, stock, available, created_at)
            VALUES(?,?,?,?,?,?, CURRENT_TIMESTAMP)
            """;

    public static final String UPDATE_PRODUCTS = """
            UPDATE products
            SET name = COALESCE(?,name),
                price = COALESCE(?, price),
                description = COALESCE(?, description),
                category = COALESCE(?, category),
                stock = COALESCE(?, stock),
                available = COALESCE(?, available)
            WHERE id = ?
            """;

    public static final String DELETE_PRODUCT = """
            DELETE FROM products
            WHERE id = ?
            """;

    public static final String SOFT_DELETE_PRODUCT = """
            UPDATE products
            SET available = false
            WHERE id = ?
            """;
}
