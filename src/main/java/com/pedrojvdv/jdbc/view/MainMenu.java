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

    private  final LoginValidation loginValidation;
    private final Scanner scanner;
    private final UserDao userDao;
    private final CartDao cartDao;
    private final DiscountDao discountDao;
    private final OrdersDao ordersDao;
    private final ProductDao productDao;
    private final SalesDao salesDao;

    UserService userService = new UserService();

    public MainMenu(UserDao userDao, CartDao cartDao, DiscountDao discountDao, OrdersDao ordersDao, ProductDao productDao, SalesDao salesDao, Scanner scanner) {
        this.userDao = userDao;
        this.cartDao = cartDao;
        this.discountDao = discountDao;
        this.ordersDao = ordersDao;
        this.productDao = productDao;
        this.salesDao = salesDao;
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
        UserValidation userValidation = new UserValidation();
        System.out.println(" -- REGISTRO -- ");

        System.out.println("NOME DE USUÁRIO: ");
        String name = scanner.nextLine();

        System.out.println("EMAIL: ");
        String email = scanner.nextLine();

        System.out.println("SENHA: ");
        String password = scanner.nextLine();
        String encryptedPassword = PasswordEncoder.encode(password);

        userService.createUser(name, email, encryptedPassword);

        System.out.println("USUÁRIO CRIADO COM SUCESSO!");
    }

}
