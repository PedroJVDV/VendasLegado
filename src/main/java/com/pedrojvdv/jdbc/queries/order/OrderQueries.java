package com.pedrojvdv.jdbc.queries.order;

public class OrderQueries {

    public static final String CREATE_ORDER = """
            INSERT INTO orders(user_id, total_price, status, created_at, updated_at)
            VALUES (?,?,?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
            """;
    public static final String FIND_BY_ID = """
            SELECT
                o.id,
                o.user_id,
                o.total_price,
                o.status,
                o.created_at,
                o.updated_at
            FROM orders
            WHERE id = ?
            """;
    public static final String UPDATE = """
            UPDATE orders
            SET user_id = ?, total_price = ?, status = ?, created_at = CURRENT_TIMESTAMP
            WHERE id = ?
            """;
    public static final String DELETE_ORDER = """
            DELETE FROM orders WHERE ID = ?
            """;
    public static final String FIND_BY_STATUS = """
            SELECT * FROM orders WHERE status = ?
            """;
    public static final String FIND_BY_PRICE_RANGE = """
            SELECT
                o.id,
                o.user_id,
                o.total_price,
                o.status,
                o.created_at,
                o.updated_at
            FROM orders o
            WHERE o.total_price BETWEEN ? AND ?
            """;
    public static final String FIND_ORDER_BY_UPDATE_RANGE = """
            SELECT
                o.id,
                o.user_id,
                o.total_price,
                o.status,
                o.created_at,
                o.updated_at
            FROM orders o
            WHERE o.updated_at BETWEEN ? AND ?
            """;
    public static final String FIND_ALL = """
            SELECT
                id,
                user_id,
                total_price,
                status,
                created_at,
                updated_at
            FROM orders
            ORDER BY created_at DESC
            """;
}
