package com.pedrojvdv.jdbc.queries;

public class UserQueries {

    public static final String FIND_USER_BY_EMAIL = """
            SELECT
                u.id,
                u.email,
                u.name,
                u.age,
                u.created_at
            FROM user u
            WHERE u.email = ?
            """;
    public static final String FIND_USER_BY_ID = """
            SELECT
                u.id,
                u.name,
                u.email,
                u.age,
                u.created_at
            FROM user u
            WHERE u.id = ?
            """;
    public static final String FIND_USER_BY_USERNAME = """
            SELECT
                u.id,
                u.name,
                u.age,
                u.email,
                u.created_at
            FROM user u
            WHERE u.name = ?
            """;
}
