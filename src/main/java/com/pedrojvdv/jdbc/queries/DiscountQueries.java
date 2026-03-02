package com.pedrojvdv.jdbc.queries;

public class DiscountQueries {

    // DISCOUNT CRUD -- TODO: comments @++
    //TODO: update status querie ??

    public static final String DEACTIVATE_EXPIRED_DISCOUNT = """
            UPDATE discount
            SET active = FALSE
            WHERE active = TRUE
            AND end_date NOT NULL
            AND end_date < CURRENT_DATE
            """;

    //queries ++
    // TODO: comments @++
    // TODO: separate by customer functions
    public static final String FIND_ALL_ACTIVE_DISCOUNTS = """
            SELECT
                p.name AS product_name,
                p.price AS product_price,
                p.price * (1 - d.percentage / 100) AS final_price
                d.percentage,
                d.type,
                d.start_date,
                d.end_date,
                d.duration_hours,
                d.active,
                d.created_at
            FROM discount d
            LEFT JOIN products p
                ON d.product_id = p.id
            WHERE d.active = TRUE
            """;
    public static final String FIND_FLASH_SALES_ADMIN = """
            SELECT
                p.name AS product_name,
                p.price AS product_price,
                p.price * (1 - d.percentage / 100) AS final_price
                d.percentage,
                d.type,
                d.start_date,
                d.end_date,
                d.duration_hours,
                d.active,
                d.created_at
            FROM discount d
            LEFT JOIN products p
                ON d.product_id = p.id
            WHERE d.type = 'FLASH_SALE'
            AND d.active = TRUE
            AND p.available = TRUE
            ORDER BY d.created_at DESC
            """;
    public static final String FIND_FLASH_SALES_CUSTOMER = """
            SELECT
                p.name AS product_name,
                p.price AS product_price,
                p.price * (1 - d.percentage / 100) AS final_price
                d.percentage,
                d.duration_hours
            FROM discount d
            LEFT JOIN products p
                ON d.product_id = p.id
            WHERE d.type = 'FLASH_SALE'
            AND d.active = TRUE
            AND p.available = TRUE
            """;
    public static final String FIND_ACTIVE_DISCOUNTS_BY_ID = """
            SELECT
                d.id,
                d.product_id,
                d.percentage,
                d.type,
                d.start_date,
                d.end_date,
                d.duration_hours,
                d.active,
                d.created_at,
                p.name AS product_name,
                p.price AS product_price
                p.price * (1 - d.percentage / 100) AS final_price
            FROM discount d
            LEFT JOIN products p
                ON d.product_id = p.id
            WHERE d.id = ?
            AND d.active = TRUE
            """;
    public static final String FIND_EXPIRED_DISCOUNTS = """
            SELECT
                d.id,
                d.product_id,
                d.percentage,
                d.type,
                d.start_date,
                d.end_date,
                d.duration_hours,
                d.active,
                d.created_at,
                p.name AS product_name,
                p.price AS product_price,
                p.price * (1 - d.percentage / 100) AS final_price
            FROM discount d
            LEFT JOIN products p
                ON d.product_id = p.id
            WHERE d.end_date NOT NULL
            AND d.end_date < CURRENT_DATE
            """;

    //TODO: enum queries ??

}
