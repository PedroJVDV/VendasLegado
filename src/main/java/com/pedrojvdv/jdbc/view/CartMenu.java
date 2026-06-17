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
            System.out.println();
            findUserCart();
            System.out.println();
            System.out.println("======================");
            System.out.println("1 - LIMPAR CARRINHO");
            System.out.println("2 - ATUALIZAR CARRINHO");
            System.out.println();
            System.out.println("---------------");
            System.out.println("3 - VOLTAR");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    deleteCart();
                    System.out.println("CARRINHO LIMPO COM SUCESSO!");
                    break;
                case 2:
                    updateCart();
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("OPÇÃO INVÁLIDA, TENTE NOVAMENTE!");
            }
        }
    }

    protected void findUserCart() throws SQLException {
        System.out.println();
        List<Cart> carts = cartService.findUserCart(user.getId());
        for (Cart cart : carts) {
            System.out.println(cart.UserToString());
        }
    }

    protected void findAdminCart() throws SQLException {
        System.out.println();
        List<Cart> carts = cartService.findCartById(user.getId());
        for (Cart cart : carts) {
            System.out.println(cart.AdmintoString());
        }
    }

    protected void findCarts()throws SQLException{
        System.out.println();
        List<Cart> carts = cartService.findCart();
        for (Cart cart : carts){
            System.out.println(cart.AdmintoString());
        }
    }

    protected void deleteCart() throws SQLException{
        System.out.println();
        cartService.deleteCart(user.getId());
        System.out.println();
        System.out.println("CARRINHO DELETADO COM SUCESSO!");
    }

    protected void createCart()throws SQLException{
        System.out.println();

        Cart cart = new Cart();
        cart.setUserId(user.getId());

        cartConstructor(cart);
        cartService.createCart(cart);
    }

    protected void updateCart()throws SQLException{
        List<Cart> cartList = cartService.findCartById(user.getId());

        System.out.println("|ATUALIZADOR DE CARRINHO|");
        System.out.println();
        System.out.println("NOVA QUANTIDADE DO CARRINHO: ");

        Cart cart = cartList.get(0);
        int quantity = Integer.parseInt(scanner.nextLine());
        cart.setQuantity(quantity);

        cartService.updateCartQuantity(cart);
        System.out.println();
        System.out.println("CARRINHO ATUALIZADO");
    }

    private void cartConstructor(Cart cart){
        System.out.println("| ADIÇÃO DE PRODUTOS NO CARRINHO | ");
        System.out.println();
        System.out.println("DIGITE A QUANTIDADE DE PEDIDOS QUE DESEJA: ");
        cart.setQuantity(Integer.parseInt(scanner.nextLine()));
    }

}


