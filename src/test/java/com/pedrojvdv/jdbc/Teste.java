package com.pedrojvdv.jdbc;

import com.pedrojvdv.jdbc.creativeJDBC.create.menuC.SystemMenu;
import com.pedrojvdv.jdbc.creativeJDBC.create.dao.UserDAO;

import java.util.Scanner;

public class Teste {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        UserDAO userDAO = new UserDAO(0, "", "");
        SystemMenu systemMenu = new SystemMenu(userDAO, scanner);

        systemMenu.exibir();

    }
}
