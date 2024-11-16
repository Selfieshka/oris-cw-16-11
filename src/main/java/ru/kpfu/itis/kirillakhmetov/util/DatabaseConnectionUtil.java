package ru.kpfu.itis.kirillakhmetov.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnectionUtil {

    private static Connection connection;

    private static final String DRIVER_NAME = "jdbc:postgresql://localhost:5432/oris-cw-16-11";
    private static final String USER = "postgres";
    private static final String PASSWORD = "5256";

    private DatabaseConnectionUtil() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(
                        DRIVER_NAME,
                        USER,
                        PASSWORD);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
