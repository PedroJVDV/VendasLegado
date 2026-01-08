package com.pedrojvdv.jdbc.creativeJDBC.create.prodTable;

import com.pedrojvdv.jdbc.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateProdTable {

    public static void main(String[] args) {

        String sql = """
                CREATE TABLE IF NOT EXISTS products(
                    regis_prod int AUTO_INCREMENT PRIMARY KEY,
                    name_prod VARCHAR(150) NOT NULL,
                    empressProd_name VARCHAR(60) NOT NULL, 
                    quantity_prod int
                  
                )
                """;

        try (java.sql.Connection connection = Connection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.execute();

            System.out.println("Tabela criada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela" + e.getMessage());
        }

    }

}
