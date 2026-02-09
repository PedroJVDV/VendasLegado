package com.pedrojvdv.jdbc.view;

import com.pedrojvdv.jdbc.dao.UserDAO;

import java.util.Scanner;

public class SystemMenu {

    private final UserDAO userDAO;
    private final Scanner scanner;

    public SystemMenu(UserDAO userDAO, Scanner scanner) {
        this.userDAO = userDAO;
        this.scanner = scanner;
    }

    public void exibir() {
        int opcao = 0;

        do {
            System.out.println(" --- MENU DE USUARIOS ---");
            System.out.println("--");
            System.out.println("1 - Criar Usuario " );
            System.out.println("2 - Lista de Usuarios ");
            System.out.println("3 - Procurar por ID ");
            System.out.println("4 - Atualizar um Usuario ");
            System.out.println("5 - Deletar um Usuario ");
            System.out.println("0 - Sair ");
            System.out.println("----");
            System.out.println("Escolha uma opção listada acima: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao){
                case 1:
                   userDAO.createUser();
                   userDAO.insertUser();
                    break;
                case 2:
                    userDAO.listUsers();
                    break;
                case 3:
                    userDAO.idSearch();
                    break;
                case 4:
                    userDAO.updateUser(scanner);
                    break;
                case 5:
                    userDAO.deleteUser();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opcão inválida!!");
            }

        } while (opcao != 0);
        scanner.close();
    }
}
