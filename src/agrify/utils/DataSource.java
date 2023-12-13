/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author abdelazizmezri
 */
public class DataSource {
    private static Connection cnx;
    private static DataSource instance;
    
    private static  String url = "jdbc:mysql://localhost:3306/agrify";
    private static String user = "root";
    private static  String password = "";
    
    private DataSource(){
        try {
            cnx = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to DB !");
        }  catch (SQLException e) {
            e.printStackTrace();
            System.out.println("unable to connect to DB !");

        }
    }
    
    public static DataSource getInstance(){
        if(instance == null){
            instance = new DataSource();
        }
        return instance;
    }
    
    public Connection getConnection(){
        return this.cnx;
    }
}
