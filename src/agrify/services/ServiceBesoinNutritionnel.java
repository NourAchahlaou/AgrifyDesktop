/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.services;

import agrify.entities.BesoinNutritionnelsEntity;
import agrify.entities.IngrediantEntity;
import agrify.utils.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alien kami sama
 */
public class ServiceBesoinNutritionnel implements IServiceBesoinNutritionnel<BesoinNutritionnelsEntity> {

    private Connection connect;
    private Database database; // Add a dataSource field

    public ServiceBesoinNutritionnel(Connection connection) {
        this.connect = connection;
    }

    public ServiceBesoinNutritionnel(Database database) {
        this.database = database;

    }

    @Override
    public void ajouter(BesoinNutritionnelsEntity besoinNutritionnel) {
        try {
            PreparedStatement statement = connect.prepareStatement("INSERT INTO `besoinnutritionnel`(`especeBesoinNutritionnel`, `statutProductionBesoinNutritionnel`, `sexeBesoinNutritionnel`, `poidsMinBesoinNutritionnel`, `poidsMaxBesoinNutritionnel`, `buteProductionBesoinNutritionnel`) VALUES (?, ?, ?, ?, ?, ?)");

            statement.setString(1, besoinNutritionnel.getEspeceBesoinNutritionnel());
            statement.setString(2, besoinNutritionnel.getStatutProductionBesoinNutritionnel());
            statement.setString(3, besoinNutritionnel.getSexeBesoinNutritionnel());
            statement.setDouble(4, besoinNutritionnel.getPoidsMinBesoinNutritionnel());
            statement.setDouble(5, besoinNutritionnel.getPoidsMaxBesoinNutritionnel());
            statement.setString(6, besoinNutritionnel.getButeProductionBesoinNutritionnel());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<BesoinNutritionnelsEntity> getSpecificColumnsFromDatabase() {
        List<BesoinNutritionnelsEntity> specificColumnsData = new ArrayList<>();

    try {
        // Create a SQL query to select specific columns
        String query = "SELECT `especeBesoinNutritionnel`, `statutProductionBesoinNutritionnel`, `sexeBesoinNutritionnel`, `poidsMinBesoinNutritionnel`, `poidsMaxBesoinNutritionnel`, `buteProductionBesoinNutritionnel` FROM `besoinnutritionnel`";

        // Create a prepared statement
        PreparedStatement statement = connect.prepareStatement(query);

        // Execute the query and get the result set
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            // Retrieve the specific columns from the result set and create BesoinNutritionnelsEntity objects
            String espece = resultSet.getString("especeBesoinNutritionnel");
            String statut = resultSet.getString("statutProductionBesoinNutritionnel");
            String sexe = resultSet.getString("sexeBesoinNutritionnel");
            double poidsMin = resultSet.getDouble("poidsMinBesoinNutritionnel");
            double poidsMax = resultSet.getDouble("poidsMaxBesoinNutritionnel");
            String bute = resultSet.getString("buteProductionBesoinNutritionnel");

            // Create a new BesoinNutritionnelsEntity and add it to the list
            BesoinNutritionnelsEntity besoinNutritionnel = new BesoinNutritionnelsEntity(espece, statut, sexe, poidsMin, poidsMax, bute);
            specificColumnsData.add(besoinNutritionnel);
        }

        // Close the statement and result set
        statement.close();
        resultSet.close();
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle exceptions here
    }

    return specificColumnsData;
    }

    @Override
     public void update(BesoinNutritionnelsEntity besoinNutritionnel) {
        try {
            // Prepare the SQL update statement
            PreparedStatement statement = connect.prepareStatement( "UPDATE `besoinnutritionnel` SET `especeBesoinNutritionnel`='?',`statutProductionBesoinNutritionnel`='?',`sexeBesoinNutritionnel`='?',`poidsMinBesoinNutritionnel`='?',`poidsMaxBesoinNutritionnel`='?',`buteProductionBesoinNutritionnel`='?'WHERE `idBesoinNutritionnel` = '?' ");

            statement.setString(1, besoinNutritionnel.getEspeceBesoinNutritionnel());
            statement.setString(2, besoinNutritionnel.getStatutProductionBesoinNutritionnel());
            statement.setString(3, besoinNutritionnel.getSexeBesoinNutritionnel());
            statement.setDouble(4, besoinNutritionnel.getPoidsMinBesoinNutritionnel());
            statement.setDouble(5, besoinNutritionnel.getPoidsMaxBesoinNutritionnel());
            statement.setString(6, besoinNutritionnel.getButeProductionBesoinNutritionnel());
            statement.setInt(7, besoinNutritionnel.getIdBesoinNutritionnel()); // Replace 7 with the correct parameter index
            statement.executeUpdate();
            statement.close();

           
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
            
        }
    }
    @Override
    public void supprimer(int idBesoinNutritionnel) {
        try {
            String deleteQuery = "DELETE FROM `besoinnutritionnel` WHERE `idBesoinNutritionnel`=?";

            PreparedStatement preparedStatement = connect.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, idBesoinNutritionnel);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
       
}
