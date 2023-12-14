/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.services;

import agrify.entities.Ration;
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
public class ServiceRation implements IServiceRation<Ration> {

    private Connection connect;
    private Database database; // Add a dataSource field

    public ServiceRation(Connection connection) {
        this.connect = connection;
    }

    public ServiceRation(Database database) {
        this.database = database;

    }

    public List<Ration> getSpecificColumnsFromDatabase() {
        List<Ration> specificColumnsData = new ArrayList<>();

        try {
            // Create a SQL query to select specific columns
            String query = "SELECT `id`, `espece_ration`, `statut_ration`, `sexe_ration`, `poids_min_ration`, `poids_max_ration`, `bute_production_ration` FROM `ration`";

            // Create a prepared statement
            PreparedStatement statement = connect.prepareStatement(query);

            // Execute the query and get the result set
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Retrieve the specific columns from the result set and create Ration objects
                int idRation = resultSet.getInt("id");
                String especeRation = resultSet.getString("espece_ration");
                String statutRation = resultSet.getString("statut_ration");
                String sexeRation = resultSet.getString("sexe_ration");
                double poidsMinRation = resultSet.getDouble("poids_min_ration");
                double poidsMaxRation = resultSet.getDouble("poids_max_ration");
                String buteProductionRation = resultSet.getString("bute_production_ration");

                // Create a new Ration and add it to the list
                Ration ration = new Ration(idRation, especeRation, statutRation, sexeRation, poidsMinRation, poidsMaxRation, buteProductionRation);
                specificColumnsData.add(ration);
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
    public void ajouter(Ration ration) {
        try {
            System.out.println(connect);
            PreparedStatement statement = connect.prepareStatement("INSERT INTO `ration`(`id`, `espece_ration`, `statut_ration`, `sexe_ration`, `poids_min_ration`, `poids_max_ration`, `bute_production_ration`) VALUES (?, ?, ?, ?, ?, ?, ?)");

            statement.setInt(1, ration.getIdRation());
            statement.setString(2, ration.getEspeceRation());
            statement.setString(3, ration.getStatutRation());
            statement.setString(4, ration.getSexeRation());
            statement.setDouble(5, ration.getPoidsMinRation());
            statement.setDouble(6, ration.getPoidsMaxRation());
            statement.setString(7, ration.getButeProductionRation());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(Ration ration) {
        try {
            // Prepare the SQL update statement for the 'ration' table
            String updateQuery = "UPDATE `ration` SET `espece_ration`=?, `statut_ration`=?, `sexe_ration`=?, `poids_min_ration`=?, `poids_max_ration`=?, `bute_production_ration`=? WHERE `id`=?";

            PreparedStatement statement = connect.prepareStatement(updateQuery);
            statement.setString(1, ration.getEspeceRation());
            statement.setString(2, ration.getStatutRation());
            statement.setString(3, ration.getSexeRation());
            statement.setDouble(4, ration.getPoidsMinRation());
            statement.setDouble(5, ration.getPoidsMaxRation());
            statement.setString(6, ration.getButeProductionRation());
            statement.setInt(7, ration.getIdRation());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
    }

    @Override
    public void supprimer(int idRation) {
        try {
            String deleteQuery = "DELETE FROM `ration` WHERE `id`=?";

            PreparedStatement preparedStatement = connect.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, idRation);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
