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
 * @author alien kami sama
 */

public class Database {
    private static java.sql.Connection cnx;
    private static Database instance;
    
    private static final  String url = "jdbc:mysql://localhost:3306/agrify";
    private static final String user = "root";
    private static final  String password = "";
    
    private Database(){
        try {
            cnx = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to DB !");
        }  catch (SQLException e) {
            e.printStackTrace();
            System.out.println("unable to connect to DB !");

        }
    }
    
    public static Database getInstance(){
        if(instance == null){
            instance = new Database();
        }
        return instance;
    }
    
    public java.sql.Connection getConnection(){
        return this.cnx;
    }
    
}



 
    
