package agrify.services;

import agrify.entities.Animal;
import agrify.utils.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceAnimal implements IServiceAnimal<Animal> {

    private final Connection connection;
    // Consider renaming 'database' to 'dataSource' for clarity
    private final Database dataSource;

    // Use constructor injection for better flexibility
    public ServiceAnimal(Connection connection) {
        this.connection = connection;
        this.dataSource = null;
    }

    // Use constructor injection for better flexibility
    public ServiceAnimal(Database dataSource) {
        this.connection = null;
        this.dataSource = dataSource;
    }

    public List<Animal> getSpecificColumnsFromDatabase() {
        List<Animal> specificColumnsData = new ArrayList<>();

        try {
            // Create a SQL query to select specific columns
            String query = "SELECT `id`, `espece`, `sexe`, `poids`, `age`, `unit_animal` FROM `animal`";

            // Use the appropriate connection (either 'connection' or 'dataSource.getConnection()')
            try (PreparedStatement statement = (connection != null) ? connection.prepareStatement(query) : dataSource.getConnection().prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    // Retrieve the specific columns from the result set and create Animal objects
                    int id = resultSet.getInt("id");
                    String espece = resultSet.getString("espece");
                    String sexe = resultSet.getString("sexe");
                    double poids = resultSet.getDouble("poids");
                    int age = resultSet.getInt("age");
                    String unitAnimal = resultSet.getString("unit_animal");

                    // Create a new Animal and add it to the list
                    Animal animal = new Animal(id, espece, sexe, poids, age, unitAnimal);
                    specificColumnsData.add(animal);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }

        return specificColumnsData;
    }

    @Override
    public void ajouter(Animal animal) {
        // Use try-with-resources to automatically close the statement
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO `animal`(`id`, `espece`, `sexe`, `poids`, `age`, `unit_animal`) VALUES (?, ?, ?, ?, ?, ?)")) {
            statement.setInt(1, animal.getId());
            statement.setString(2, animal.getEspece());
            statement.setString(3, animal.getSexe());
            statement.setDouble(4, animal.getPoids());
            statement.setInt(5, animal.getAge());
            statement.setString(6, animal.getUnit_animal());

            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(Animal animal) {
        try {
            // Prepare the SQL update statement
            String updateQuery = "UPDATE `animal` SET `espece`=?, `sexe`=?, `poids`=?, `age`=?, `unit_animal`=? WHERE `id`=?";

            try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
                statement.setString(1, animal.getEspece());
                statement.setString(2, animal.getSexe());
                statement.setDouble(3, animal.getPoids());
                statement.setInt(4, animal.getAge());
                statement.setString(5, animal.getUnit_animal());
                statement.setInt(6, animal.getId());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
    }

    @Override
    public void supprimer(int idAnimal) {
        try {
            String deleteQuery = "DELETE FROM `animal` WHERE `id`=?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setInt(1, idAnimal);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Animal> getAllAnimal() {
        List<Animal> animals = new ArrayList<>();
        try {
            String query = "SELECT * FROM animal";

            // Create a prepared statement
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

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
            if (connection != null) {
                String query = "SELECT COUNT(*) AS total FROM gestation";
                try (PreparedStatement statement = connection.prepareStatement(query);
                     ResultSet resultSet = statement.executeQuery()) {

                    if (resultSet.next()) {
                        totalAnimals = resultSet.getInt("total");
                    }
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
