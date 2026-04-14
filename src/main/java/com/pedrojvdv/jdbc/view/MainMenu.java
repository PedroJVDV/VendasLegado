package com.pedrojvdv.jdbc.view;

import com.pedrojvdv.jdbc.dao.*;
import com.pedrojvdv.jdbc.model.User;
import com.pedrojvdv.jdbc.model.enums.UserRole;
import com.pedrojvdv.jdbc.util.PasswordEncoder;
import com.pedrojvdv.jdbc.validation.LoginValidation;
import com.pedrojvdv.jdbc.validation.UserValidation;

import java.sql.SQLException;
import java.util.Scanner;

public class MainMenu {

    private final LoginValidation loginValidation;
    private OrdersDao ordersDao;
    private UserDao userDao;
    private Scanner scanner;

    public MainMenu(UserDao userDao, OrdersDao ordersDao, Scanner scanner) {
        this.userDao = userDao;
        this.ordersDao = ordersDao;
        this.scanner = scanner;
        this.loginValidation = new LoginValidation();
    }

    public void start()throws SQLException {
        boolean running = true;

        while(running){
            System.out.println("\n --- E-COMMERCE 1.0 ---  ");
            System.out.println("1 - Login");
            System.out.println("2 - Registrar");
            System.out.println("3 - Sair");

            int choice = Integer.parseInt(scanner.nextLine());

            switch(choice){
                case 1:
            }
        }
    }

    private void loginMenu()throws SQLException{
        System.out.println(" -- LOGIN -- ");
        System.out.println("EMAIL: ");
        String email = scanner.nextLine();

        System.out.println("SENHA: ");
        String password = scanner.nextLine();

        User user = loginValidation.authenticate(email, password);

        if (user.getRole() == UserRole.ADMIN ){
            AdminMenu adminMenu = new AdminMenu();
            // TODO: adminMenu.show(user);
        }else{
            UserMenu userMenu = new UserMenu();
            // TODO: userMenu.show(user);
        }
    }

    private void registerUser()throws SQLException{
        System.out.println(" -- REGISTRO -- ");

        System.out.println("NOME DE USUÁRIO: ");
        String name = scanner.nextLine();

        System.out.println("EMAIL: ");
        String email = scanner.nextLine();

        System.out.println("SENHA: ");
        String password = scanner.nextLine();

        String encryptedPassword = PasswordEncoder.encode(password);
        UserValidation userValidation = new UserValidation();

        User newUser = new User();
        userValidation.validate(newUser);
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);
    }

}
