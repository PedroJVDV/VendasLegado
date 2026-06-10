package com.pedrojvdv.jdbc.view;

import com.pedrojvdv.jdbc.model.Discount;
import com.pedrojvdv.jdbc.model.User;

import java.sql.SQLException;
import java.util.Scanner;

public class UserMenu {

    private final User user;
    private final Scanner scanner;

    public UserMenu(User user, Scanner scanner) {
        this.user = user;
        this.scanner = scanner;
    }

    public void showUserMenu() throws SQLException {
        boolean running = true;

        while (running) {
            System.out.printf("Bem-Vindo!, %s%n", user.getName());
            System.out.println();
            System.out.println();
            System.out.println("1 - MENU DE PRODUTOS ");
            System.out.println("2 - MENU DE DESCONTOS ");
            System.out.println("----------------------");
            System.out.println("3 - MEU CARRINHO");
            System.out.println("====================");
            System.out.println("9 - VOLTAR");
            System.out.println("====================");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    ProductMenu productMenu = new ProductMenu(user, scanner);
                    productMenu.show();
                    break;
                case 2:
                    DiscountMenu discountMenu = new DiscountMenu(user, scanner);
                    discountMenu.show();
                case 3:
                    CartMenu cartMenu = new CartMenu(user, scanner);
                    cartMenu.showUserCart();
                case 9:
                    running = false;
                    break;
            }
        }
    }
}