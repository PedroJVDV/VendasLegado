package com.pedrojvdv.jdbc.dao;

import com.pedrojvdv.jdbc.model.User;
import com.pedrojvdv.jdbc.queries.UserCrudQueries;
import com.pedrojvdv.jdbc.queries.UserQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao{

    private final Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public void createUser(User user) throws SQLException {
        String sql = UserCrudQueries.CREATE_USER;

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setObject(3, user.getAge());
            stmt.executeUpdate();
        }
    }

    public void updateUser(User user) throws SQLException{
        String sql = UserCrudQueries.UPDATE_USER;

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setObject(1, user.getName());
            stmt.setObject(2, user.getEmail());
            stmt.setObject(3, user.getAge());
            stmt.setLong(4, user.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteUser(User user) throws SQLException{
        String sql = UserCrudQueries.DELETE_USER;

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setLong(1, user.getId());
            stmt.executeUpdate();
        }
    }

    public List<User> findUserByEmail(String email)throws SQLException{
       return executeQuery(UserQueries.FIND_USER_BY_EMAIL, email);
    }

    public List<User> findUserById(Long id)throws SQLException{
        return executeQuery(UserQueries.FIND_USER_BY_ID, id);
    }

    public List<User> findUserByUsername(String name)throws SQLException{
        return executeQuery(UserQueries.FIND_USER_BY_USERNAME, name);
    }

    private List<User> executeQuery(String sql, Object... args)throws SQLException{
        List<User> userList = new ArrayList<>();

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            for (int i = 0; i < args.length ; i++) {
                stmt.setObject(i+1,args[i] );
            }
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                User user = mapResultToUser(rs);
                userList.add(user);

            }
            return userList;
        }
    }

    private User mapResultToUser(ResultSet rs) throws SQLException{
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setAge(rs.getInt("age"));
        user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return user;
    }
}
