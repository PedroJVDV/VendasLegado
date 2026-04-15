package com.pedrojvdv.jdbc.queries.user;

public class UserCrudQueries {

    public static final String CREATE_USER = """
            INSERT INTO users (name, password, email, age, created_at)
            VALUES(?, ?, ?, ?, CURRENT_TIMESTAMP)
            """;
    public static final String UPDATE_USER = """
            UPDATE users
            SET name = COALESCE(?, name), password = COALESCE(?, password), email = COALESCE(?, email), age = COALESCE(?, age)
            WHERE id = ?
            """;
    public static final String DELETE_USER = """
            DELETE FROM users
            WHERE id = ?
            """;
}
