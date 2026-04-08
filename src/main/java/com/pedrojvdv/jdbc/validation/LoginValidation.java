package com.pedrojvdv.jdbc.validation;

import com.pedrojvdv.jdbc.dao.UserDao;
import com.pedrojvdv.jdbc.model.User;
import com.pedrojvdv.jdbc.model.enums.UserRole;
import com.pedrojvdv.jdbc.util.PasswordEncoder;

import java.sql.SQLException;

public class LoginValidation {

    private final UserDao userDao;

    public LoginValidation(UserDao userDao) {
        this.userDao = userDao;
    }

    public User authenticate (String email, String password)throws SQLException {
        User user = userDao.findUserByEmail(email);

        if (user == null){
            return null;
        }
        if (!PasswordEncoder.match(password, user.getPassword())){
            return null;
        }
        return user;
    }

    public boolean isAdmin(User user){
        return user != null && user.getRole() == UserRole.ADMIN;
    }

    public boolean isUser(User user){
        return user != null && user.getRole() == UserRole.USER;
    }
    
}
