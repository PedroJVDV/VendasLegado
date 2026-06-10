package com.pedrojvdv.jdbc.queries.cart;

public class CartQueries {

    public static final String CREATE = """
            INSERT INTO cart(product_id, user_id, quantity, added_at)
            VALUES(?, ?, ?, CURRENT_TIMESTAMP)
            """;
    public static final String DELETE = """
            DELETE FROM cart
            WHERE id = ?
            """;
    public static final String SOFT_DELETE = """
            DELETE FROM cart
            WHERE user_id = ?
            """;

    public static final String FIND_CART_PRODUCTS = """
            SELECT
                c.quantity,
                p.name AS product_name,
                p.price AS original_price,
                d.percentage AS product_discount,
                p.price * (1 - COALESCE(d.percentage, 0) / 100) AS final_price,
                d.type AS discount_type
            FROM cart c
            LEFT JOIN products p
                ON c.product_id = p.id
            LEFT JOIN discount d
                ON p.id = d.product_id
            """;
    public static final String FIND_CART_USERID = """
            SELECT
                id,
                product_id,
                user_id,
                quantity,
                added_at
            FROM cart
            WHERE user_id = ?
            """;
    public static final String UPDATE_QUANTITY = """
            UPDATE cart
            SET quantity = ?
            WHERE id = ?
            """;
}
