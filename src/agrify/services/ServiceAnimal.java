/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.services;

import agrify.entities.Animal;
import agrify.entities.BesoinNutritionnelsEntity;
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
public class ServiceAnimal implements IServiceAnimal<Animal> {

    private Connection connect;
    private Database database; // Add a dataSource field

    public ServiceAnimal(Connection connection) {
        this.connect = connection;
    }

    public ServiceAnimal(Database database) {
        this.database = database;

    }

    public List<Animal> getSpecificColumnsFromDatabase() {
        List<Animal> specificColumnsData = new ArrayList<>();

        try {
            // Create a SQL query to select specific columns
            String query = "SELECT `id`, `espece`, `sexe`, `poids`, `age`, `unit_animal` FROM `animal`";

            // Create a prepared statement
            PreparedStatement statement = connect.prepareStatement(query);

            // Execute the query and get the result set
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Retrieve the specific columns from the result set and create Animal objects
                int id = resultSet.getInt("id");
                String espece = resultSet.getString("espece");
                String sexe = resultSet.getString("sexe");
                double poids = resultSet.getDouble("poids");
                int age = resultSet.getInt("age");
                String unit_animal = resultSet.getString("unit_animal");
                

                // Create a new Animal and add it to the list
                Animal animal = new Animal(id, espece, sexe, poids, age, unit_animal);

                specificColumnsData.add(animal);

            }

            // Close the statement and result set
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }

        return specificColumnsData;
    }

    @Override
    public void ajouter(Animal animal) {
        try {
            System.out.println(connect);
            PreparedStatement statement = connect.prepareStatement("INSERT INTO `animal`(`id`, `espece`, `sexe`, `poids`, `age`, `unit_animal`) VALUES (?, ?, ?, ?, ?, ?)");

            statement.setInt(1, animal.getId());
            statement.setString(2, animal.getEspece());
            statement.setString(3, animal.getSexe());
            statement.setDouble(4, animal.getPoids());
            statement.setInt(5, animal.getAge());
            statement.setString(6, animal.getUnit_animal());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(Animal animal) {
        try {
            // Prepare the SQL update statement
            String updateQuery = "UPDATE `animal` SET `espece`=?, `sexe`=?, `poids`=?, `age`=?, `unit_animal`=? WHERE `id`=?";

            PreparedStatement statement = connect.prepareStatement(updateQuery);
            statement.setString(1, animal.getEspece());
            statement.setString(2, animal.getSexe());
            statement.setDouble(3, animal.getPoids());
            statement.setInt(4, animal.getAge());
            statement.setString(5, animal.getUnit_animal());
            statement.setInt(6, animal.getId());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
    }

    @Override
    public void supprimer(int idAnimal) {
        try {
            String deleteQuery = "DELETE FROM `animal` WHERE `id`=?";

            PreparedStatement preparedStatement = connect.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, idAnimal);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Animal> getAllAnimal() {
    List<Animal> animals = new ArrayList<>();
    try {
        String query = "SELECT * FROM animal";

        // Create a prepared statement
        PreparedStatement statement = connect.prepareStatement(query);

        // Execute the query and get the result set
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            // Use a constructor or factory method to create Animal instances
            Animal animal = new Animal(
                resultSet.getInt("id"),
                resultSet.getString("espece"),
                resultSet.getDouble("poids"),
                resultSet.getString("unit_animal")
            );

            animals.add(animal);
        }
    } catch (SQLException e) {
        // Handle any potential database exceptions
        e.printStackTrace();
    }

    return animals;
}
    public int getTotalAnimalsInGestation() {
        int totalAnimals = 0;

        try {
            if (connect != null) {
                String query = "SELECT COUNT(*) AS total FROM gestation";
                PreparedStatement statement = connect.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    totalAnimals = resultSet.getInt("total");
                }
            } else {
                System.err.println("Database connection is null");
            }
        } catch (SQLException e) {
            // Handle any potential database exceptions
            e.printStackTrace();
        }

        return totalAnimals;
    }
   



}
