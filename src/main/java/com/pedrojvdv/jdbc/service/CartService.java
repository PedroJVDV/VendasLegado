package com.pedrojvdv.jdbc.service;

import com.pedrojvdv.jdbc.dao.CartDao;
import com.pedrojvdv.jdbc.exception.CartException;
import com.pedrojvdv.jdbc.exception.UserException;
import com.pedrojvdv.jdbc.model.Cart;

import java.sql.SQLException;
import java.util.List;

public class CartService {

    private final CartDao cartDao;

    public CartService() {
        this.cartDao = new CartDao();
    }

    public void createCart(Cart cart)throws SQLException, CartException {
        cartDao.createCart(cart);
    }

    public void updateCartQuantity(Cart cart) throws SQLException, CartException{
        cartDao.updateCartQuantity(cart);
    }

    public void deleteCart(Long id)throws SQLException, CartException{
        cartDao.deleteCart(id);
    }

    public void softDeleteCart(Long userId)throws SQLException, CartException{
        cartDao.softDeleteCart(userId);
    }


    public void deleteCartByUser(Long userId) throws SQLException {
        if (userId == null) {
            throw new UserException("Usuário inválido ou inexistente");
        }
        cartDao.deleteCartByUser(userId);
    }


    public List<Cart> findCartById(Long id)throws SQLException, CartException{
        if (id == null){
            throw new CartException("ID DE CARRINHO INVÁLIDO OU INEXISTENTE!");
        }
        return cartDao.findCartById(id);
    }

    public List<Cart> findUserCart(Long userId)throws SQLException{
        if (userId == null) {
            throw new UserException("Usuário inválido ou inexistente");
        }
        return cartDao.findCartProducts(userId);
    }

}

