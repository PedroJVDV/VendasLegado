package com.pedrojvdv.jdbc.view;

import com.pedrojvdv.jdbc.model.User;

import java.sql.SQLException;
import java.util.Scanner;

public class AdminMenu {

    private final DiscountMenu discountMenu;
    private final ProductMenu productMenu;
    private final Scanner scanner;
    private final User user;

    public AdminMenu(User user, Scanner scanner) {
        this.user = user;
        this.scanner = scanner;
        this.discountMenu = new DiscountMenu(user, scanner);
        this.productMenu = new ProductMenu(user, scanner);
    }

    public void showAdminMenu() throws SQLException {
        boolean running = true;

        while (running) {
            System.out.println(" || SISTEMA DE ADMINISTRADOR ||");
            System.out.println("");

            System.out.println("1 - ADMINISTRAÇÃO DE PRODUTOS");
            System.out.println("2 - ADMINISTRAÇÃO DE DESCONTOS");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    adminProductMenu();
                    break;
                case 2:
                    adminDiscountMenu();
                    break;
                case 7:
                    running = false;
                    break;
            }
        }
    }

    private void adminDiscountMenu() throws SQLException {
        boolean running = true;

        while (running) {

            System.out.println("1 - BUSCAR DESCONTOS ");
            System.out.println("2 - GERENCIAR PRODUTOS ");
            System.out.println("3 - VOLTAR");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    discountMenu.searchMenuAdmin();
                    break;
                case 2:
                    discountMenu.crudAdminMenu();
                    break;
                case 3:
                    running = false;
                    break;
            }
        }
    }

    private void adminProductMenu() throws SQLException {
        boolean running = true;

        while (running) {
            System.out.println("1 - CATALOGO DE PRODUTOS");
            System.out.println("2 - BUSCAR PRODUTOS");
            System.out.println("3 - GERENCIAR PRODUTOS");
            System.out.println("4 - VOLTAR");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    productMenu.showCatalogMenu();
                    break;
                case 2:
                    productMenu.showAdminSearchMenu();
                    break;
                case 3:
                    productMenu.showManageMenu();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Opção Inválida, tente novamente!");
            }
        }
    }

}
