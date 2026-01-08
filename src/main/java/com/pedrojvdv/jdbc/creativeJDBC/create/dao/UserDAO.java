package com.pedrojvdv.jdbc.creativeJDBC.create.dao;

import com.pedrojvdv.jdbc.Connection;
import com.pedrojvdv.jdbc.creativeJDBC.create.userCreative.Users;
import com.pedrojvdv.jdbc.creativeJDBC.create.validation.UserValidation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserDAO extends Users {

    public UserDAO(int id, String email, String nome) {
        super(id, email, nome);
    }

    public void createUser() {
        try {

            Scanner scanner = new Scanner(System.in);

            System.out.println("Informe o seu nome de usuario: ");
            setNome(scanner.nextLine());

            System.out.println("Informe o seu Email de acesso: ");
            setEmail(scanner.nextLine());

            System.out.println("Informe sua Idade: ");
            setIdade(scanner.nextInt());

        } catch (Exception e) {
            System.out.println("Não foi possível definir usuario!");
        }

    }

    public void insertUser() {
        try {

            Users users = new Users(getId(), getEmail(), getNome());
            users.setIdade(getIdade());

            List<String> errorsValidate = UserValidation.listValidate(users);
            if (!errorsValidate.isEmpty()) {
                System.out.println("Erros de validação encontrados. Digite os dados corretamente!");
                return;
            }


            if (!emailExists(users.getEmail())) {
                System.out.println("Usuario incluido com sucesso!");
            } else {
                emailExists(users.getEmail());
                {
                    System.out.println("Email já cadastrado. Tente novamente!");
                }
            }

            String sql = "INSERT INTO usuarios (nome, email, idade) VALUES (?, ?, ?)";

            ParamsDAO paramsDAO = new ParamsDAO();
            int id = paramsDAO.insertParam(sql, getNome(), getEmail(), getIdade());
            System.out.println("ID gerado: " + id);

        } catch (Exception e) {
            System.out.println("Não foi possível criar usuario! " + e.getMessage());
        }
    }

    public void listUsers() {

        String sql = "SELECT * FROM usuarios";
        List<Users> usuarios = new ArrayList<>();

        try (java.sql.Connection connection = Connection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet ResultSet = stmt.executeQuery()) {

            // passamos todos os usuarios da QUERY para dentro da lista de usuarios,
            // * somente id, email e nome!
            while (ResultSet.next()) {
                Users usuario = new Users(ResultSet.getInt("id")
                        , ResultSet.getString("email")
                        , ResultSet.getString("nome"));
                usuarios.add(usuario);
            }

            //imprime a consulta no console
            for (Users user : usuarios) {
                System.out.println("ID: " + user.getId() +
                        " | Nome: " + user.getNome() +
                        " | Email: " + user.getEmail());
            }
        } catch (Exception e) {
            System.out.println("Não foi possivel listar usuarios! " + e.getMessage());
        }
    }

    // busca usuarios por ID

    public void idSearch() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o ID que deseja buscar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        String sql = "SELECT * FROM usuarios WHERE id = ?";

        try (java.sql.Connection connection = Connection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Usuário localizado!");
                System.out.println("-----");
                System.out.println(" |ID: " + resultSet.getInt("id"));
                System.out.println(" | Nome: " + resultSet.getString("nome"));
                System.out.println(" | Email: " + resultSet.getString("email"));
                System.out.println("-----");
                resultSet.getInt("id");
                resultSet.getString("email");
                resultSet.getString("nome");

            } else {
                System.out.println("-----");
                System.out.println("Usuario não foi encontrado!");
                System.out.println("-----");

            }

        } catch (Exception e) {
            System.out.println("-----");
            System.out.println("Erro inesperado ao encontrar usuario:" + e.getMessage());
            System.out.println("-----");
        }
    }

    public void updateUser(Scanner scanner) {

        try {
            System.out.println("Digite o ID do usuario que deseja fazer alterações: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            if (id <= 0) {
                System.out.println("Digite um ID existente!");
                return;
            }

            String novoNome = null;
            String novoEmail = null;
            Integer novaIdade = null;

            System.out.println("Deseja alterar o nome de usuario? (S/N) ");
            String respostaNome = scanner.nextLine();
            if (respostaNome.equalsIgnoreCase("S")) {
                System.out.println("Digite o novo nome de usuario: ");
                novoNome = scanner.nextLine();
            }

            System.out.println("Deseja alterar o email do usuario? (S/N)");
            String respostaEmail = scanner.nextLine();
            if (respostaEmail.equalsIgnoreCase("S")) {
                System.out.println("Digite o novo email: ");
                novoEmail = scanner.nextLine();
            }

            System.out.println("Deseja alterar a idade? (S/N)");
            String respostaIdade = scanner.nextLine();
            if (respostaIdade.equalsIgnoreCase("S")) {
                System.out.println("Digite a nova idade: ");
                novaIdade = scanner.nextInt();
            }

            Users temp = new Users(id,
                    novoEmail != null ? novoEmail : getEmail(),
                    novoNome != null ? novoNome : getNome());
            if (novaIdade != null) temp.setIdade(novaIdade);

            List<String> errors = UserValidation.listValidate(temp);
            if (!errors.isEmpty()) {
                System.out.println("Erros de validação:");
                for (String err : errors) System.out.println(" - " + err);
                return;
            }

            // COALESCE --> se o valor do parametro for nulo, ou seja, não for inserido, o objeto permanecera igual!
            String sql = "UPDATE usuarios SET " +
                    "nome = COALESCE(?, nome)," +
                    "email = COALESCE(?, email)," +
                    "idade = COALESCE(?, idade)" +
                    "WHERE id = ?";

            ParamsDAO paramsDAO = new ParamsDAO();
            paramsDAO.updateParam(sql, novoNome, novoEmail, novaIdade, id);

            System.out.println("-----");
            System.out.println("Dados atualizados com sucesso!!");
            System.out.println("-----");

        } catch (Exception e) {
            System.out.println("-----");
            System.out.println("Erro ao atualizar usuario!" + e.getMessage());
            System.out.println("-----");
        }
    }

    public void deleteUser() {

        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Informe o ID do usuario que deseja DELETAR: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            if (id <= 0) {
                System.out.println("Digite um ID válido!");
                return;
            }

            System.out.println("Você realmente deseja DELETAR este usuario? (S/N)");
            String respostaDelete = scanner.nextLine();
            if (respostaDelete.equalsIgnoreCase("S")) {
                String sql = "DELETE FROM usuarios WHERE id = ?";

                ParamsDAO paramsDAO = new ParamsDAO();
                paramsDAO.deleteParam(sql, id);

                System.out.println("-----");
                System.out.println("O usuario foi deletado com sucesso!");
                System.out.println("-----");

            } else {
                System.out.println("-----");
                System.out.println("Operação cancelada!");
                System.out.println("-----");
            }

        } catch (Exception e) {
            System.out.println("-----");
            System.out.println("Não foi possível deletar esse usuário!!" + e.getMessage());
            System.out.println("-----");
        }

    }

    // verifica se o email existe e alega se sim ou não, caso não exista o email sera adicionado ao banco, caso exista não adiciona!

    public boolean emailExists(String email) {

        String sql = "SELECT 1 FROM usuarios WHERE email = ? LIMIT 1";
        try (java.sql.Connection conn = Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            System.out.println("Aviso: falha ao verificar unicidade de email: " + e.getMessage());
            return false;
        }
    }

}


