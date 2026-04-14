package com.pedrojvdv.jdbc.view;

import com.pedrojvdv.jdbc.config.ConnectionFactory;
import com.pedrojvdv.jdbc.dao.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class MainStart {

    public static void main(String[] args) {
        try {
            Connection connection = ConnectionFactory.getConnection();

            Scanner scanner = new Scanner(System.in);

            CartDao cartDao = new CartDao();
            DiscountDao discountDao = new DiscountDao();
            OrdersDao ordersDao = new OrdersDao();
            ProductDao productDao =  new ProductDao();
            SalesDao salesDao = new SalesDao();
            UserDao userDao = new UserDao();

            MainMenu menu = new MainMenu(userDao,ordersDao, scanner);

            menu.start();
            scanner.close();
            connection.close();

        } catch (SQLException e) {
           System.out.println("Erro ao conectar com banco de dados");
        }
    }
}
