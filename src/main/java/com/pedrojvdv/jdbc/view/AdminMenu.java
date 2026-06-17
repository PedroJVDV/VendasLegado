package com.pedrojvdv.jdbc.view;

import com.pedrojvdv.jdbc.model.User;

import java.sql.SQLException;
import java.util.Scanner;

public class AdminMenu {

    private final DiscountMenu discountMenu;
    private final ProductMenu productMenu;
    private final CartMenu cartMenu;
    private final Scanner scanner;
    private final User user;

    public AdminMenu(User user, Scanner scanner) {
        this.user = user;
        this.scanner = scanner;
        this.discountMenu = new DiscountMenu(user, scanner);
        this.productMenu = new ProductMenu(user, scanner);
        this.cartMenu = new CartMenu(user, scanner);
    }

    public void showAdminMenu() throws SQLException {
        boolean running = true;

        while (running) {
            System.out.println(" || SISTEMA DE ADMINISTRADOR ||");
            System.out.println();
            System.out.println("-------------------------------");
            System.out.println("1 - ADMINISTRAÇÃO DE PRODUTOS");
            System.out.println();
            System.out.println("2 - ADMINISTRAÇÃO DE DESCONTOS");
            System.out.println();
            System.out.println("3 - ADMINISTRAR CARRINHOS");
            System.out.println();
            System.out.println("7 - VOLTAR");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    adminProductMenu();
                    break;
                case 2:
                    adminDiscountMenu();
                    break;
                case 3:
                    adminCartMenu();
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

    public void adminCartMenu() throws SQLException {
        System.out.printf(user.getName(), "ESTE É SEU GERENCIADOR DE CARRINHOS");
        System.out.println();
        System.out.println();
        System.out.println("======================");
        System.out.println();
        System.out.println("======================");
        System.out.println("1 - CARRINHOS POR |ID|");
        System.out.println("2 - USUÁRIOS COM CARRINHO ATIVO");
        System.out.println("3 - DELETAR CARRINHO");

        boolean running = true;

        while (running) {

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    cartMenu.findAdminCart();
                    break;
                case 2:
                    cartMenu.findCarts();
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("OPÇÃO INVÁLIDA, TENTE NOVAMENTE!");
            }
        }
    }
}
