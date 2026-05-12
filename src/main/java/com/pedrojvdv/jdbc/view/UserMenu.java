package com.pedrojvdv.jdbc.view;

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
            System.out.println("1 - MENU DE PRODUTOS");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    ProductMenu productMenu = new ProductMenu(user, scanner);
                    productMenu.show();
                    break;
                case 2:
                    running = false;
                    break;
            }
        }
    }

    public void showAdminMenu()throws SQLException{
        boolean running = true;

        while(running){
            System.out.println(" || SISTEMA DE ADMINISTRADOR ||");
            System.out.println("");

            System.out.println("1 - ADMINISTRAÇÃO DE PRODUTOS");
            System.out.println("2 - ");

            int choice = Integer.parseInt(scanner.nextLine());
            ProductMenu productMenu = new ProductMenu(user, scanner);

            switch (choice){
                case 1:
                    productMenu.adminProductMenu();
            }
        }
    }
}