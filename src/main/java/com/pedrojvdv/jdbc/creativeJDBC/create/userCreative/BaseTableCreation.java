package com.pedrojvdv.jdbc.creativeJDBC.create.userCreative;

import com.pedrojvdv.jdbc.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class BaseTableCreation {

    public static void main(String[] args) throws SQLException {
//        java.sql.Connection connection = null ;

            String sql = """
                CREATE TABLE IF NOT EXISTS users(
                id INT AUTO_INCREMENT PRIMARY KEY,
                nome VARCHAR(150) NOT NULL,
                email VARCHAR(150)  UNIQUE,
                idade int
                    
                )
                """;
        try (java.sql.Connection conexao = Connection.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.execute();

            System.out.println("Conexão estabelecida! ");
            System.out.println("Tabela criada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro inesperado ao criar a tabela!" +e.getMessage());
        }

    }
}
