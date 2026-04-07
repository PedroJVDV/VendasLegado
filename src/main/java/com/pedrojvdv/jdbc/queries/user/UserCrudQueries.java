package com.pedrojvdv.jdbc.queries.user;

public class UserCrudQueries {

    public static final String CREATE_USER = """
            INSERT INTO user (name, user_password, email, age, created_at)
            VALUES(?, ?, ?, ? CURRENT_TIMESTAMP)
            """;
    public static final String UPDATE_USER = """
            UPDATE user
            SET name = COALESCE(?, name), user_password = COALESCE(?, user_password), email = COALESCE(?, email), age = COALESCE(?, age)
            WHERE id = ?
            """;
    public static final String DELETE_USER = """
            DELETE FROM user
            WHERE id = ?
            """;
}
