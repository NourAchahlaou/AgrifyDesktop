/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.services;

import agrify.entities.AnimauxEnGestationEntity;
import agrify.utils.Database;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alien kami sama
 */
public class ServiceAnimauxEnGestation implements IServiceAnimauxEnGestation<AnimauxEnGestationEntity>{
     private Connection connect;
    private Database database; // Add a dataSource field

    public ServiceAnimauxEnGestation(Connection connection) {
        this.connect = connection;
    }

    public ServiceAnimauxEnGestation(Database database) {
        this.database = database;

    }
    
    public List<AnimauxEnGestationEntity> getSpecificColumnsFromDatabase() {
    List<AnimauxEnGestationEntity> specificColumnsData = new ArrayList<>();

    try {
        // Create a SQL query to select specific columns
        String query = "SELECT `espece`, `statut`, `preparationVêlage`, `vêlagePrévu`, `dateAvertissement` FROM `animauxengestationentity` ";

        // Create a prepared statement
        PreparedStatement statement = connect.prepareStatement(query);

        // Execute the query and get the result set
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            // Retrieve the specific columns from the result set and create AnimauxEnGestationEntity objects
            String espece = resultSet.getString("espece");
            Date preparationVêlage = resultSet.getDate("preparationVêlage");
            Date vêlagePrévu = resultSet.getDate("vêlagePrévu");
            Date dateAvertissement = resultSet.getDate("dateAvertissement");

            // Create a new AnimauxEnGestationEntity and add it to the list
            AnimauxEnGestationEntity animauxEnGestation = new AnimauxEnGestationEntity(espece, preparationVêlage, vêlagePrévu, dateAvertissement);
            specificColumnsData.add(animauxEnGestation);
        }

        // Close the statement and result set
        statement.close();
        resultSet.close();
    } catch (SQLException e) {
        e.printStackTrace(); // Handle exceptions appropriately
    }

    return specificColumnsData;
}

    

   
    
}
