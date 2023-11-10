package com.example.demo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {

    private static final String URL_TEMPLATE = "jdbc:mysql://%s:3306/api?useSSL=false&allowPublicKeyRetrieval=true&autoReconnect=true&failOverReadOnly=false&maxReconnects=10";
    private static final String USER = "exxea";
    private static final String PASSWORD = "exxea";

    // Singleton instance
    private static DBConnection instance;

    private Connection connection;

    private static final boolean isRunningInDocker = isRunningInDocker();

    private static boolean isRunningInDocker() {
        String containerName = System.getenv("HOSTNAME"); // Docker sets HOSTNAME env variable
        return containerName != null && containerName.length() > 0;
    }

    private DBConnection() {
        try {
            String ipAddress = isRunningInDocker ? "172.31.1.2" : "127.0.0.1";
            String url = String.format(URL_TEMPLATE, ipAddress);
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, USER, PASSWORD);
            connection.setAutoCommit(true);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize database connection.", e);
        }
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
