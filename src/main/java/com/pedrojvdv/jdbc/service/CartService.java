package com.pedrojvdv.jdbc.service;

import com.pedrojvdv.jdbc.dao.CartDao;
import com.pedrojvdv.jdbc.exception.UserException;
import com.pedrojvdv.jdbc.model.Cart;

import java.sql.SQLException;
import java.util.List;

public class CartService {

    private final CartDao cartDao;

    public CartService() {
        this.cartDao = new CartDao();
    }

    public void deleteCartByUser(Long userId) throws SQLException {
        if (userId == null) {
            throw new UserException("Usuário inválido ou inexistente");
        }
        cartDao.deleteCartByUser(userId);
    }

    public List<Cart> findUserCart(Long userId)throws SQLException{
        if (userId == null) {
            throw new UserException("Usuário inválido ou inexistente");
        }
        return cartDao.findCartProducts(userId);
    }
}

