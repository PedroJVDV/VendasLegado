package com.pedrojvdv.jdbc.service;

import com.pedrojvdv.jdbc.dao.UserDao;
import com.pedrojvdv.jdbc.exception.UserException;
import com.pedrojvdv.jdbc.model.User;
import com.pedrojvdv.jdbc.validation.UserValidation;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private final UserDao userDao;
    private final UserValidation userValidation;

    public UserService() {
        this.userDao = new UserDao();
        this.userValidation = new UserValidation();
    }

    public void createUser(User user) throws UserException, SQLException {
        userValidation.validate(user);
        userDao.createUser(user);
    }

    public void updateUser(User user) throws UserException, SQLException {
        userValidation.validate(user);
        userDao.updateUser(user);
    }

    public void deleteUser(User user) throws UserException, SQLException{
        userDao.deleteUser(user);
    }

    public List<User> findUserByEmail(String email) throws UserException, SQLException{
        return userDao.findUserByEmail(email);
    }

    public List<User> findUserById(Long id)throws UserException, SQLException{
        return userDao.findUserById(id);
    }

    public List<User> findUserByUsername(String name) throws UserException, SQLException{
        return userDao.findUserByUsername(name);
    }
}
