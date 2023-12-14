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
            PreparedStatement statement = connect.prepareStatement("INSERT INTO `nutritional_needs`(`species_needs`, `production_status_needs`, `sex_needs`, `minimum_weight_needs`, `maximum_weight_needs`, `production_goal_needs`) VALUES (?, ?, ?, ?, ?, ?)");

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
        String query = "SELECT `id`, `species_needs`, `production_status_needs`, `sex_needs`, `minimum_weight_needs`, `maximum_weight_needs`, `production_goal_needs` FROM `nutritional_needs`";

        // Create a prepared statement
        PreparedStatement statement = connect.prepareStatement(query);

        // Execute the query and get the result set
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            // Retrieve the specific columns from the result set and create BesoinNutritionnelsEntity objects
            String espece = resultSet.getString("species_needs");
            String statut = resultSet.getString("production_status_needs");
            String sexe = resultSet.getString("sex_needs");
            double poidsMin = resultSet.getDouble("minimum_weight_needs");
            double poidsMax = resultSet.getDouble("maximum_weight_needs");
            String bute = resultSet.getString("production_goal_needs");

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
public void update(BesoinNutritionnelsEntity besoinNutritionnel)  {
    try {
        // Prepare the SQL update statement
        PreparedStatement statement = connect.prepareStatement(
                "UPDATE `nutritional_needs` " +
                "SET `species_needs`=?, `production_status_needs`=?, `sex_needs`=?, " +
                "`minimum_weight_needs`=?, `maximum_weight_needs`=?, `production_goal_needs`=? " +
                "WHERE `idBesoinNutritionnel`=?"
        );

        statement.setString(1, besoinNutritionnel.getEspeceBesoinNutritionnel());
        statement.setString(2, besoinNutritionnel.getStatutProductionBesoinNutritionnel());
        statement.setString(3, besoinNutritionnel.getSexeBesoinNutritionnel());
        statement.setDouble(4, besoinNutritionnel.getPoidsMinBesoinNutritionnel());
        statement.setDouble(5, besoinNutritionnel.getPoidsMaxBesoinNutritionnel());
        statement.setString(6, besoinNutritionnel.getButeProductionBesoinNutritionnel());
        statement.setInt(7, besoinNutritionnel.getIdBesoinNutritionnel());

        statement.executeUpdate();
        statement.close();

    } catch (SQLException e) {
        e.printStackTrace(); // Handle exceptions appropriately
    }


    }
    @Override
    public void supprimer(int idBesoinNutritionnel) {
        try {
            String deleteQuery = "DELETE FROM `nutritional_needs` WHERE `idBesoinNutritionnel`=?";

            PreparedStatement preparedStatement = connect.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, idBesoinNutritionnel);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
       
}
