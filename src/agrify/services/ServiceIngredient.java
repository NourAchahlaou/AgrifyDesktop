/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.services;


import agrify.entities.IngrediantEntity;
import agrify.utils.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author alien kami sama
 */
public class ServiceIngredient implements IServiceIngredient<IngrediantEntity>  {
    private Connection connect;
    private Database database; // Add a dataSource field


    public ServiceIngredient(Connection connection) {
        this.connect = connection;
    }
    
    public ServiceIngredient(Database database) {
    this.database = database;
    
    
    }
   

    @Override
public void ajouter(IngrediantEntity ingredient) {
    try {
        System.out.println(connect);
        PreparedStatement statement = connect.prepareStatement("INSERT INTO `ingrediants`(`idIngredient`, `nameIngredient`, `itemQuantityIngredient`, `unitIngredient`, `costIngredient`, `loadedByIngredient`, `descriptionIngredient`, `typeIngredient`,`nutrimentPrincipalIngredient`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

        statement.setInt(1, ingredient.getIdIngredient());
        statement.setString(2, ingredient.getNameIngredient());
        statement.setDouble(3, ingredient.getItemQuantityIngredient());
        statement.setString(4, ingredient.getUnitIngredient());
        statement.setDouble(5, ingredient.getCostIngredient());
        statement.setString(6, ingredient.getLoadedByIngredient());
        statement.setString(7, ingredient.getDescriptionIngredient());
        statement.setString(8, ingredient.getTypeIngredient());
        statement.setString(9, ingredient.getNutrimentPrincipalIngredient());
        

        statement.executeUpdate();
        statement.close();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
//aficher 
    public List<IngrediantEntity> getSpecificColumnsFromDatabase() {
       List<IngrediantEntity> specificColumnsData = new ArrayList<>();

    try {
        // Create a SQL query to select specific columns
        String query = "SELECT `nameIngredient`, `costIngredient`, `loadedByIngredient`,`nutrimentPrincipalIngredient` FROM `ingrediants`";

        // Create a prepared statement
        PreparedStatement statement = connect.prepareStatement(query);

        // Execute the query and get the result set
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            // Retrieve the specific columns from the result set and create BesoinNutritionnelsEntity objects
            String nom = resultSet.getString("nameIngredient");
            String source = resultSet.getString("loadedByIngredient");
            String prix = resultSet.getString("costIngredient");
            String nutrimentPrincipal = resultSet.getString("nutrimentPrincipalIngredient");

            // Create a new BesoinNutritionnelsEntity and add it to the list
            IngrediantEntity ingrediant = new IngrediantEntity(nom,prix,source,nutrimentPrincipal);
            specificColumnsData.add(ingrediant);
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
public void update(IngrediantEntity ingrediantEntity) {
    try {
        // Prepare the SQL update statement
        String updateQuery = "UPDATE `ingrediants` SET `nameIngredient`=?, `itemQuantityIngredient`=?, `unitIngredient`=?, `costIngredient`=?, `loadedByIngredient`=?, `descriptionIngredient`=?, `typeIngredient`=?, `nutrimentPrincipalIngredient`=? WHERE `idIngredient`=?";

        PreparedStatement statement = connect.prepareStatement(updateQuery);
        statement.setString(1, ingrediantEntity.getNameIngredient());
        statement.setDouble(2, ingrediantEntity.getItemQuantityIngredient());
        statement.setString(3, ingrediantEntity.getUnitIngredient());
        statement.setDouble(4, ingrediantEntity.getCostIngredient());
        statement.setString(5, ingrediantEntity.getLoadedByIngredient());
        statement.setString(6, ingrediantEntity.getDescriptionIngredient());
        statement.setString(7, ingrediantEntity.getTypeIngredient());
        statement.setString(8, ingrediantEntity.getNutrimentPrincipalIngredient());
        statement.setInt(9, ingrediantEntity.getIdIngredient());

        statement.executeUpdate();
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace(); // Handle exceptions appropriately
    }
}
private List<IngrediantEntity> ingrediants;
@Override
public void supprimer(int idIngredient) {
    try {
        String deleteQuery = "DELETE FROM `ingrediants` WHERE `idIngredient`=?";

        PreparedStatement preparedStatement = connect.prepareStatement(deleteQuery);
        preparedStatement.setInt(1, idIngredient);

        preparedStatement.executeUpdate();
        preparedStatement.close();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
 public ServiceIngredient(List<IngrediantEntity> ingredients) {
        this.ingrediants = ingredients;
    }
 public List<IngrediantEntity> getAllIngredients() throws SQLException {
    List<IngrediantEntity> ingrediants = new ArrayList<>();
    try {
        String query = "SELECT * FROM ingrediants"; // Correct the table name to "ingredients"

        // Create a prepared statement
        PreparedStatement statement = connect.prepareStatement(query);

        // Execute the query and get the result set
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            // Create IngrediantEntity objects and populate them with data from the result set
            IngrediantEntity ingredient = new IngrediantEntity();
            ingredient.setIdIngredient(resultSet.getInt("idIngredient"));
            ingredient.setNameIngredient(resultSet.getString("nameIngredient"));
            ingredient.setItemQuantityIngredient(resultSet.getDouble("itemQuantityIngredient")); // Retrieve the "quantity" field

            ingrediants.add(ingredient);
        }
    } catch (SQLException e) {
        // Handle any potential database exceptions
        e.printStackTrace();
    }

    return ingrediants;
}
  public List<String> getIngredientNamesFromDatabase() {
    List<String> ingredientNames = new ArrayList<>();
    
    try {
        if (connect != null) {
            String query = "SELECT name FROM ingrediants"; // Correct table name if needed
            PreparedStatement statement = connect.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String ingredientName = resultSet.getString("name");
                ingredientNames.add(ingredientName);
            }
        } else {
            System.err.println("Database connection is null");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return ingredientNames;
}



  


 }

