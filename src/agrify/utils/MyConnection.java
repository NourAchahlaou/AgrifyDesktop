/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.utils;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class MyConnection {
         Connection cnx;
    private static MyConnection instance;

    private String url = "jdbc:mysql://localhost:3306/agrify";
    private String user = "root";
    private String password = "";


    private MyConnection(){
        try {
            cnx = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Connected to DB !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static MyConnection getInstance(){
        if(instance == null){
            instance = new MyConnection();
        }
        return instance;
    }

    public Connection getCon(){
        return cnx;
    }
}
