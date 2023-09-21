package com.bank.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    private static Connection connection;
    private static String USER="aymane";

    private static String PASS="aymane@123";
    private static String HOST="jdbc:postgresql://localhost:5432/BANK";

    private JDBCConnection(){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(HOST, USER, PASS);
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if(connection == null)
            connection = new JDBCConnection().getConnection();
        return connection;
    }
}


