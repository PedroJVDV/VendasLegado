package com.pedrojvdv.jdbc.view;

import com.pedrojvdv.jdbc.model.User;
import com.pedrojvdv.jdbc.model.Cart;
import com.pedrojvdv.jdbc.service.CartService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CartMenu {

    private final User user;
    private final Scanner scanner;
    private final CartService cartService;


    public CartMenu(User user, Scanner scanner) {
        this.user = user;
        this.scanner = scanner;
        this.cartService = new CartService();
    }

    public void showUserCart() throws SQLException {
        boolean running = true;

        while (running) {
            System.out.printf(user.getName(), "ESTE É SEU CARRINHO DE PRODUTOS");
            System.out.println();
            System.out.println();
            System.out.println("======================");
            findUserCart();
            System.out.println("======================");
            System.out.println("1 - LIMPAR CARRINHO");
            System.out.println("---------------");
            System.out.println("2 - VOLTAR");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    cartService.deleteCartByUser(user.getId());
                    System.out.println("CARRINHO LIMPO COM SUCESSO!");
                    break;
                case 2:
                    running = false;
                    break;
            }
        }
    }

    private void findUserCart() throws SQLException {
        System.out.println();
        List<Cart> carts = cartService.findUserCart(user.getId());
        for (Cart cart : carts) {
            System.out.println(cart.UserToString());
        }
    }


}
