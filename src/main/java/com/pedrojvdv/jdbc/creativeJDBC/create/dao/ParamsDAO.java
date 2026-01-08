package com.pedrojvdv.jdbc.creativeJDBC.create.dao;

import com.pedrojvdv.jdbc.Connection;

import java.sql.*;

public class ParamsDAO {

    public int insertParam(String sql, Object... args) {

        try {
            //guarda o ID gerado automaticamente pelo banco de dados
            PreparedStatement stmt = getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            addParams(stmt, args);

            System.out.println("-----");
            System.out.println("SQL final: " + stmt);
            System.out.println("-----");

            if (stmt.executeUpdate() > 0) {
                ResultSet resultSet = stmt.getGeneratedKeys();

                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
            return -1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void addParams(PreparedStatement stmt, Object[] args) throws SQLException {

        int index = 1;

        //se o argumento passado for uma String ele cai aqui e define o caminho correto pro SQL!!
        for (Object arg : args) {
            if (arg instanceof String) {
                stmt.setString(index, (String) arg);
                //se o argumento passado for um INT ele cai aqui e define o caminho correto pro SQL!!
            } else if (arg instanceof Integer) {
                stmt.setInt(index, (Integer) arg);
            }
            // no final de cada operação a gente quer que o Index seja acrescentado na linha do MYSQL, ex: index 1 foi passado uma String
            // e no Index 2 vai ser passado um Int, ele detecta automaticamente e cria o espaço necessario, ex:

            // INSERT INTO tabela (nome, idade) VALUES (?, ?)
            // a String vai para o primeiro ? (nome, ?)
            // o double vai pro segundo ?(nome, idade)

            index++;
        }
    }

    public int updateParam(String sql, String novoNome, String novoEmail, Integer novaIdade, int id) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(sql);

            if (novoNome != null) {
                stmt.setString(1, novoNome);
            } else {
                stmt.setNull(1, Types.VARCHAR);
            }

            if (novoEmail != null) {
                stmt.setString(2, novoEmail);
            } else {
                stmt.setNull(2, Types.VARCHAR);
            }

            if (novaIdade != null) {
                stmt.setInt(3, novaIdade);
            } else {
                stmt.setNull(3, Types.INTEGER);
            }

            stmt.setInt(4, id);

            System.out.println("-----");
            System.out.println("SQL final: " + stmt);
            System.out.println("-----");

            return stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteParam(String sql, int id) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setInt(1, id);

            System.out.println("------");
            System.out.println("SQL final: " + stmt);
            System.out.println("------");

            return stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    java.sql.Connection getConnection() {
        return Connection.getConnection();
    }

}
