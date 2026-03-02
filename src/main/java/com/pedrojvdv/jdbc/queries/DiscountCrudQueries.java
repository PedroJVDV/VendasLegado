package com.pedrojvdv.jdbc.queries;

public class DiscountCrudQueries {

    public static final String CREATE_DISCOUNT = """
            INSERT INTO discount (product_id, percentage, active, created_at, start_date, end_date)
            VALUES(?,?,?,CURRENT_TIMESTAMP,?,?)
            """;
    // TODO: separated functions by admin
    public static final String UPDATE_DISCOUNT = """
            UPDATE discount
            SET type = COALESCE(?, type), percentage = COALESCE(?, percentage), duration_hours = COALESCE(?, duration_hours)
            WHERE id = ?
            """;
    public static final String UPDATE_END_DATE = """
            UPDATE discount
            SET end_date = ?
            WHERE id = ?
            """;
    public static final String DELETE_DISCOUNT = """
            DELETE from discount
            WHERE id = ?
            """;

    public static final String SOFT_DELETE_DISCOUNT = """
             UPDATE discount
             SET active = FALSE
             WHERE id = ?
            """;
}
