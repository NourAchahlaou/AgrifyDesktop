/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.services;

/**
 *
 * @author alien kami sama
 */
import agrify.entities.ValeurNutritionnelEntity;
import agrify.utils.Database;



import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ServiceValeurNutritionnel implements IServiceValeurNutritionnel<ValeurNutritionnelEntity> {
    private Connection connect;
    private Database database; // Add a dataSource field


    public ServiceValeurNutritionnel(Connection connection) {
        this.connect = connection;
    }
    
    public ServiceValeurNutritionnel(Database database) {
    this.database = database;
  
    }
    @Override
    public void ajouter(ValeurNutritionnelEntity valeurNutritionnel) {
        try {
            PreparedStatement statement = connect.prepareStatement("INSERT INTO `valeurnutritionnel`( `pb`, `fb`, `adf`, `ndf`, `ms`, `eb`, `ca`, `p`, `mg`, `k`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            statement.setDouble(1, valeurNutritionnel.getPb());
            statement.setDouble(2, valeurNutritionnel.getFb());
            statement.setDouble(3, valeurNutritionnel.getAdf());
            statement.setDouble(4, valeurNutritionnel.getNdf());
            statement.setDouble(5, valeurNutritionnel.getMs());
            statement.setDouble(6, valeurNutritionnel.getEb());
            statement.setDouble(7, valeurNutritionnel.getCa());
            statement.setDouble(8, valeurNutritionnel.getP());
            statement.setDouble(9, valeurNutritionnel.getMg());
            statement.setDouble(10, valeurNutritionnel.getK());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
