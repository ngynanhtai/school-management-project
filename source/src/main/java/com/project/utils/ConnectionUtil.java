package com.project.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
@Slf4j
public class ConnectionUtil {

    public static Connection getDbConnection(Driver driver, String url, String username, String password) {
        Connection conn = null;
        try {
            DriverManager.registerDriver(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            log.error(e.getMessage());
            return null;
        }
        return conn;
    }

    public static void closeDbConnection(PreparedStatement statement, Connection connection) {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            log.error("Failed to close Connection: " + e.getMessage());
        }
    }

}
