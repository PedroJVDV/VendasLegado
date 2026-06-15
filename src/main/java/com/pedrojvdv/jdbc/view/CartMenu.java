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

    public void showAdminCart() throws SQLException {
        boolean running = true;

        while (running) {
            System.out.printf(user.getName(), "ESTE É SEU GERENCIADOR DE CARRINHOS");
            System.out.println();
            System.out.println();
            System.out.println("======================");
            System.out.println();
            System.out.println("======================");
            System.out.println("1 - CARRINHOS POR |ID|");
            System.out.println("2 - USUÁRIOS COM CARRINHO ATIVO");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    findAdminCart();
                    break;
                case 2:
                    deleteCart();
                    break;
                case 3:

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

    private void findAdminCart() throws SQLException {
        System.out.println();
        List<Cart> carts = cartService.findCartById(user.getId());
        for (Cart cart : carts) {
            System.out.println(cart.AdmintoString());
        }
    }

    private void deleteCart() throws SQLException{
        System.out.println();
        cartService.deleteCart(user.getId());
        System.out.println();
        System.out.println("CARRINHO DELETADO COM SUCESSO!");
    }

    private void createCart()throws SQLException{
        System.out.println();
        cartConstructor();


        // TODO: IMPLEMENTANDO E PENSANDO NO CREATE/UPDATE!
    }

    private void updateCart()throws SQLException{
        System.out.println();
        cartService.updateCartQuantity();
    }

    private void cartConstructor(Cart cart){
        cart.getProductId();
        cart.getUserId();
        System.out.println("| ADIÇÃO DE PRODUTOS NO CARRINHO | ");
        System.out.println();
        System.out.println("DIGITE A QUANTIDADE DE PEDIDOS QUE DESEJA: ");
        cart.setQuantity(Integer.parseInt(scanner.nextLine()));
    }

}


