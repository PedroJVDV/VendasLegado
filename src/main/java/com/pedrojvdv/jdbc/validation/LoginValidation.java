package com.pedrojvdv.jdbc.validation;

import com.pedrojvdv.jdbc.dao.UserDao;
import com.pedrojvdv.jdbc.exception.AuthException;
import com.pedrojvdv.jdbc.exception.UserException;
import com.pedrojvdv.jdbc.model.User;
import com.pedrojvdv.jdbc.model.enums.UserRole;
import com.pedrojvdv.jdbc.util.PasswordEncoder;

import java.sql.SQLException;

public class LoginValidation {

    private final UserDao userDao;
    private final UserValidation userValidation;

    public LoginValidation() {
        this.userDao = new UserDao();
        this.userValidation = new UserValidation();
    }

    public User authenticate(String email, String password) throws SQLException {

        userValidation.validateEmail(email);
        userValidation.validatePassword(password);

        User user = userDao.findUserByEmail(email);
        userValidation.validate(user);

        if (user == null){
            throw new AuthException("Usuário inexistente!");
        }

        if (!PasswordEncoder.match(password, user.getPassword())) {
            throw new AuthException("SENHA INCORRETA, TENTE NOVAMENTE!");
        }
        return user;
    }

    // TODO: encode conditions
    public boolean isAdmin(User user) {
        if (user.getPassword().equals("ADMIN#2026001")) {
            return user.getRole() == UserRole.ADMIN;
        }
        return false;
    }

    public boolean isUser(User user) {
        return user != null && user.getRole() == UserRole.USER;
    }
}
