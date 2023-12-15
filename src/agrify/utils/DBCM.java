/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCM {
    private static DBCM instance;
    private Connection connection;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/agrify";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "";

    private DBCM() {
        try {
            connection = DriverManager
                .getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DBCM getInstance() {
        if (instance == null) {
            instance = new DBCM();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }
}
