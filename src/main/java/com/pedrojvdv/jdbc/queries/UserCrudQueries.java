package com.pedrojvdv.jdbc.queries;

public class UserCrudQueries {

    public static final String CREATE_USER = """
            INSERT INTO user (name, email, age, created_at)
            VALUES(?, ?, ?, CURRENT_TIMESTAMP)
            """;
    public static final String UPDATE_USER = """
            UPDATE user
            SET name = COALESCE(?, name), email = COALESCE(?, email), age = COALESCE(?, age)
            WHERE id = ?
            """;
    public static final String DELETE_USER = """
            DELETE FROM user
            WHERE id = ?
            """;
}
