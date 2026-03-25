package com.pedrojvdv.jdbc.queries;

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
    public static final String FIND_CART_ID = """
            SELECT * FROM cart
            WHERE id = ?
            """;
    public static final String FIND_CART_USERID = """
            SELECT * FROM cart
            WHERE user_id = ?
            """;
    public static final String UPDATE_QUANTITY = """
            UPDATE cart
            SET quantity = ?
            WHERE id = ?
            """;
}
