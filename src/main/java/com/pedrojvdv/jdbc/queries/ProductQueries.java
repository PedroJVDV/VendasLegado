package com.pedrojvdv.jdbc.queries;

public class ProductQueries {

    public static final String FIND_ALL_PRODUCTS = """
            SELECT
                p.name,
                p.price,
                p.stock,
                p.category,
                p.description,
                COALESCE(d.percentage, 0) AS discount_percentage,
                p.price * (1 - COALESCE(d.percentage, 0) / 100) AS final_price
            FROM products p
            LEFT JOIN discount d
                ON p.id = d.product_id AND d.active = TRUE
            WHERE p.available = TRUE
            ORDER BY p.name, p.price
            """;

    public static final String FIND_BY_CATEGORY = """
            SELECT
                p.name,
                p.price,
                COALESCE(p.category, 'no category') AS product_category,
                COALESCE(p.description, 'no description') AS product_description,
                p.stock,
                COALESCE(d.percentage, 0) AS discount_percentage
                p.price * (1 - COALESCE(d.percentage, 0) / 10 ) AS final_price
            FROM products p
            LEFT JOIN discount d
                ON p.id = d.product_id AND d.active = TRUE
            WHERE category = ?
            AND p.available = TRUE
            ORDER BY p.name
            """;

    public static final String FIND_DISCOUNT_PRODUCTS = """
            SELECT
                p.name,
                p.price,
                p.available,
                p.percentage,
                p.price*( 1 - COALESCE(d.percentage, 0) / 100 ) AS final_price
            FROM products p
            INNER JOIN discount d
                ON p.id = d.product_id AND d.active = TRUE
            WHERE p.available = TRUE
            ORDER BY final_price ASC
            """;


    public static final String FIND_BY_DATE = """
            
            """;

    public static final String FIND_SOLD_PRODUCTS = """
            
            """;

    public static final String FIND_BY_STOCK = """
            
            """;

    public static final String FIND_BY_NAME = """
            """;

    public static final String FIND_BY_PRICE = """
            
            """;

}
