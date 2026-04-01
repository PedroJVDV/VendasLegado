package com.pedrojvdv.jdbc.queries.sale;

public class SalesQueries {

    public static final String CREATE = """
            INSERT INTO sales (order_id, product_id, discount_id, quantity, total_price, sale_date)
            VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP)
            """;
    public static final String UPDATE = """
            UPDATE sales
            SET order_id = ?, product_id = ?, discount_id = ?, quantity = ?, total_price = ?, sale_date = CURRENT_TIMESTAMP
            WHERE id = ?
            """;
    public static final String DELETE = """
            DELETE FROM sales
            WHERE id = ?
            """;
    public static final String FIND_ALL = """
            SELECT
                id,
                order_id,
                product_id,
                discount_id,
                quantity,
                total_price,
                sale_date
            FROM sales
            ORDER BY quantity DESC
            """;
    public static final String FIND_BY_QUANTITY_RANGE = """
            SELECT
                id,
                order_id,
                product_id,
                discount_id,
                total_price,
                sale_date,
                quantity
            FROM sales
            WHERE quantity BETWEEN ? AND ?
            """;
    public static final String FIND_BY_TOTAL_PRICE_RANGE = """
            SELECT
                id,
                order_id,
                product_id,
                discount_id,
                quantity,
                sale_date,
                total_price
            FROM sales
            WHERE total_price BETWEEN ? AND ?
            """;
    public static final String FIND_BY_DATE_RANGE = """
            SELECT
                id,
                order_id,
                product_id,
                discount_id,
                quantity,
                total_price,
                sale_date
            FROM sales
            WHERE sale_date BETWEEN ? AND ?
            """;
    public static final String FIND_BY_ORDER_ID = """
            SELECT
                id,
                product_id,
                discount_id,
                quantity,
                total_price,
                sale_date
            FROM sales
            WHERE order_id = ?
            """;
    public static final String FIND_BY_ID = """
            SELECT
                id,
                product_id,
                discount_id,
                order_id,
                quantity,
                total_price,
                sale_date
            FROM sales
            WHERE id = ?
            """;


}
