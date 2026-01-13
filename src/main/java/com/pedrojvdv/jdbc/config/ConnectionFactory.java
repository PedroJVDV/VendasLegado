package com.pedrojvdv.jdbc.config;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    public static java.sql.Connection getConnection() {
        try {
            Properties properties = getProperties();
            final String url = properties.getProperty("db.url");
            final String user = properties.getProperty("db.user");
            final String password = properties.getProperty("db.password");

            return DriverManager.getConnection(url, user, password);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Properties getProperties() throws IOException {
        Properties properties = new Properties();
        String way = "/connection.properties";
        properties.load(ConnectionFactory.class.getResourceAsStream(way));
        return properties;
    }

    public static void main(String[] args) {

        try {
            java.sql.Connection connection = getConnection();
            System.out.println("Conexão realizada!");

            connection.close();
            System.out.println("Conexão finalizada!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
