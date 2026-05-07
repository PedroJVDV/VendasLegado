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
            MainMenu menu = new MainMenu(scanner);

            menu.start();
            scanner.close();
            connection.close();

        } catch (SQLException e) {
           System.out.println("Erro ao conectar com banco de dados");
           e.printStackTrace();
        }
    }
}
