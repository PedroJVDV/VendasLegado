package com.pedrojvdv.jdbc.view;

import com.pedrojvdv.jdbc.dao.*;
import com.pedrojvdv.jdbc.model.Cart;
import com.pedrojvdv.jdbc.model.User;
import com.pedrojvdv.jdbc.model.enums.UserRole;
import com.pedrojvdv.jdbc.service.UserService;
import com.pedrojvdv.jdbc.util.PasswordEncoder;
import com.pedrojvdv.jdbc.validation.LoginValidation;
import com.pedrojvdv.jdbc.validation.UserValidation;

import java.sql.SQLException;
import java.util.Scanner;

public class MainMenu {

    private LoginValidation loginValidation;
    private UserValidation userValidation;
    private Scanner scanner;

    UserService userService = new UserService();

    public MainMenu(Scanner scanner) {
        this.scanner = scanner;
        this.loginValidation = new LoginValidation();
        this.userValidation = new UserValidation();
    }

    public MainMenu() {
    }

    public void start() throws SQLException {
        boolean running = true;

        while (running) {
            System.out.println("\n --- E-COMMERCE 1.0 ---  ");
            System.out.println("1 - Login");
            System.out.println("2 - Registrar");
            System.out.println("3 - Sair");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    loginMenu();
                    break;
                case 2:
                    registerUser();
                    break;
                case 3:
                    running = false;
                    break;
            }
        }
    }

    private void loginMenu() throws SQLException {
        System.out.println(" -- LOGIN -- ");

        System.out.println("EMAIL: ");
        String email = scanner.nextLine();

        System.out.println("SENHA: ");
        String password = scanner.nextLine();

        User user = loginValidation.authenticate(email, password);

        if (user.getRole() == UserRole.ADMIN) {
            AdminMenu adminMenu = new AdminMenu();
        } else {
            UserMenu userMenu = new UserMenu(user, scanner);
            userMenu.show();
        }
    }

    private void registerUser() throws SQLException {
        System.out.println(" -- REGISTRO -- ");

        System.out.println("NOME DE USUÁRIO: ");
        String name = scanner.nextLine();

        System.out.println("EMAIL: ");
        String email = scanner.nextLine();

        System.out.println("SENHA: ");
        String password = scanner.nextLine();

        userValidation.validateName(name);
        userValidation.validateEmail(email);
        userValidation.validatePassword(password);
        String encryptedPassword = PasswordEncoder.encode(password);

        userService.createUser(name, email, encryptedPassword);

        System.out.println("USUÁRIO CRIADO COM SUCESSO!");
    }

}
