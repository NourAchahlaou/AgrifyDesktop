/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.services;

import agrify.entities.Field;
import agrify.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tbagh
 */

public class ServiceField implements IServiceField<Field> {
    private Connection connect;
    private DataSource dataSource;


public ServiceField(Connection connection)
    
{
        this.connect = connection;
    }

public ServiceField(DataSource dataSource) 
    
    {
        this.dataSource = dataSource;
    }


@Override
public void ajouter(Field field) 
  {
        try {
            PreparedStatement statement = connect.prepareStatement("INSERT INTO field(field_Nom, field_type, field_Superficie, Field_quantity) VALUES (?, ?, ?, ?)");

            statement.setString(1, field.getField_Nom());
            statement.setString(2, field.getField_type());
            statement.setDouble(3, field.getField_Superficie());
            statement.setInt(4, field.getField_quantity());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
  }



@Override
 public void modifier(Field field) 
    {
        try {
            String updateQuery = "UPDATE field SET field_Nom=?, field_type=?, field_Superficie=?, Field_quantity=? WHERE field_Id=?";

            PreparedStatement preparedStatement = connect.prepareStatement(updateQuery);
            preparedStatement.setString(1, field.getField_Nom());
            preparedStatement.setString(2, field.getField_type());
            preparedStatement.setDouble(3, field.getField_Superficie());
            preparedStatement.setInt(4, field.getField_quantity());
            preparedStatement.setLong(5, field.getField_Id());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

 
 
 
@Override
public void supprimer(int fieldId) 
    {
        try {
            String deleteQuery = "DELETE FROM field WHERE field_Id=?";

            PreparedStatement preparedStatement = connect.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, fieldId);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }



@Override
public Field getOne(int fieldId)
    {
        try {
            String selectQuery = "SELECT * FROM field WHERE field_Id=?";
            PreparedStatement preparedStatement = connect.prepareStatement(selectQuery);
            preparedStatement.setInt(1, fieldId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Field field = new Field();
                field.setField_Id(rs.getInt("field_Id"));
                field.setField_Nom(rs.getString("field_Nom"));
                field.setField_type(rs.getString("field_type"));
                field.setField_Superficie(rs.getDouble("field_Superficie"));
                field.setField_quantity(rs.getInt("Field_quantity"));
                return field;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }




@Override
public List<Field> getAll() 
    {
        List<Field> fields = new ArrayList<>();
        try {
            String selectQuery = "SELECT * FROM field";
            PreparedStatement preparedStatement = connect.prepareStatement(selectQuery);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Field field = new Field();
                field.setField_Id(rs.getInt("field_Id"));
                field.setField_Nom(rs.getString("field_Nom"));
                field.setField_type(rs.getString("field_type"));
                field.setField_Superficie(rs.getDouble("field_Superficie"));
                field.setField_quantity(rs.getInt("Field_quantity"));
                fields.add(field);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return fields;
    }
    

    
    
@Override
public List<Field> searchFieldsByName(String name) throws SQLException 
{
    List<Field> fields = new ArrayList<>();
    String selectQuery = "SELECT * FROM field WHERE field_Nom LIKE ?";

    try (PreparedStatement preparedStatement = connect.prepareStatement(selectQuery)) {
        preparedStatement.setString(1, "%" + name + "%"); 

        try (ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                Field field = new Field();
                field.setField_Id(rs.getInt("field_Id"));
                field.setField_Nom(rs.getString("field_Nom"));
                field.setField_type(rs.getString("field_type"));
                field.setField_Superficie(rs.getDouble("field_Superficie"));
                field.setField_quantity(rs.getInt("Field_quantity"));
                fields.add(field);
            }
        }
    } catch (SQLException ex) {
        System.out.println("An error occurred while searching for fields by name.");
        ex.printStackTrace();
        throw ex;
    }

    return fields;
}

    
@Override
public void supprimerByName(String field_Nom) 
{
    try 
     {
        String deleteQuery = "DELETE FROM `field` WHERE `field_Nom` = ?";

        PreparedStatement preparedStatement = connect.prepareStatement(deleteQuery);
        preparedStatement.setString(1, field_Nom);

        preparedStatement.executeUpdate();
        preparedStatement.close();
     } 
    catch (SQLException ex) 
     {
        System.out.println(ex.getMessage());
     }
}
}
