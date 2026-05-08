package com.pedrojvdv.jdbc.view;

import com.pedrojvdv.jdbc.dao.*;
import com.pedrojvdv.jdbc.model.User;

import java.sql.SQLException;
import java.util.Scanner;

public class UserMenu {

    private User user;
    private Scanner scanner;

    public UserMenu(User user, Scanner scanner) {
        this.user = user;
        this.scanner = scanner;
    }

    public void show() throws SQLException {
        boolean running = true;

        while (running) {
            System.out.printf("Bem-Vindo!, %s%n", user.getName());
            System.out.println();
            System.out.println("1 - MENU DE PRODUTOS");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    ProductMenu productMenu = new ProductMenu(scanner);
                    productMenu.show();
                    break;
//                case 2:
//                    discountMenu();
//                    break;
//                case 3:
//                    cartMenu();
//                    break;
//                case 4:
//                    orderMenu();
                case 2:
                    running = false;
                    break;
            }
        }
    }


}
