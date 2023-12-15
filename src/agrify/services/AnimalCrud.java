/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.services;

import agrify.entities.Animal_1;
import agrify.entities.Health;
import agrify.entities.SexeAnimal;
import agrify.utils.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import tools.MyConnection;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;   
 

/**
 *
 * @author Ahmed Raies 
 */
public class AnimalCrud {
    
    Connection cnx2;
    public AnimalCrud(){
       cnx2 =DataSource.getInstance().getConnection();
   }
    
    public void ajouterAnimal(Animal_1 a){
        String sql = "INSERT INTO animal_stock (nom_animal,sexe_animal,age_animal,poids_animal,health,date_entree_stock,prix, image)"+
            "VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cnx2.prepareStatement(sql);
            pst.setString(1, a.getNomanimal());
            pst.setString(2, a.getSexeanimal().toString());
            pst.setInt(3, a.getAgeanimal());
            pst.setFloat(4, a.getPoidsanimal());
            pst.setString(5, a.getHealthanimal().toString());
            pst.setDate(6, (Date) a.getDateEntreeStock());
            pst.setFloat(7, a.getPrix());
            pst.setString(8, a.getImage());
            
            pst.executeUpdate();
            System.out.println("L'animal est ajouté avec succès !");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }   
      
    public List<Animal_1> afficherAnimal(){
    List<Animal_1> myList = new ArrayList<>();
    String sql ="SELECT * FROM animal_stock";
        try {
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Animal_1 a = new Animal_1();
                a.setIdanimal(rs.getInt(1));
                a.setNomanimal(rs.getString("nom_animal"));
                a.setSexeanimal(SexeAnimal.valueOf(rs.getString("sexe_animal")));
                a.setAgeanimal(rs.getInt("age_animal"));
                a.setPoidsanimal(rs.getFloat("poids_animal"));
                a.setHealthanimal(Health.valueOf(rs.getString("health")));
                a.setDateEntreeStock(rs.getDate("date_entree_stock"));
                myList.add(a);      
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    return myList;
 } 
    
    public Animal_1 chercherAnimal(int id) {
    String sql = "SELECT * FROM animal_stock WHERE id=?";
    try {
        PreparedStatement pst = cnx2.prepareStatement(sql);
        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            Animal_1 a = new Animal_1();
            a.setIdanimal(rs.getInt("id"));
            a.setNomanimal(rs.getString("nom_animal"));
            a.setSexeanimal(SexeAnimal.valueOf(rs.getString("sexe_animal")));
            a.setAgeanimal(rs.getInt("age_animal"));
            a.setPoidsanimal(rs.getFloat("poids_animal"));
            a.setHealthanimal(Health.valueOf(rs.getString("health")));
            a.setDateEntreeStock(rs.getDate("date_entree_stock"));
            return a;
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return null; // Renvoie null si l'animal n'est pas trouvé
}
       
    public void modifierAnimal(Animal_1 a) {
    String sql = "UPDATE animal_stock SET nom_animal=?, sexe_animal=?, age_animal=?, poids_animal=?, health=?, date_entree_stock=? ,prix=? ,image=? WHERE id=?";
    try {
        PreparedStatement pst = cnx2.prepareStatement(sql);
        pst.setString(1, a.getNomanimal());
        pst.setString(2, a.getSexeanimal().toString());
        pst.setInt(3, a.getAgeanimal());
        pst.setFloat(4, a.getPoidsanimal());
        pst.setString(5, a.getHealthanimal().toString());
        pst.setDate(6, (Date) a.getDateEntreeStock());
        pst.setFloat(7, a.getPrix());
        pst.setString(8, a.getImage());
        pst.setInt(9, a.getIdanimal());
        

        int rowsAffected = pst.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("L'animal a été modifié avec succès !");
        } else {
            System.out.println("Aucune modification n'a été effectuée.");
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}
    
    public void supprimerAnimal(int id) {
    String sql = "DELETE FROM animal_stock WHERE id=?";
    try {
        PreparedStatement pst = cnx2.prepareStatement(sql);
        pst.setInt(1, id);

        int rowsAffected = pst.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("L'animal a été supprimé avec succès !");
        } else {
            System.out.println("Aucune suppression n'a été effectuée.");
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}

    public int getGeneratedAnimalId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}





