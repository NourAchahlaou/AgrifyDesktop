/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.services;
import agrify.entities.StockDivers;
import agrify.entities.Health;
import agrify.entities.NomDivStock;
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
public class StockDiversCrud {
    
    Connection cnx2;
    public StockDiversCrud(){
       cnx2 =MyConnection.getInstance().getCnx();
   }
    
    
     public void ajouterStockDivers(StockDivers sd){
        String sql = "INSERT INTO stock_divers (nom_sd,quantite_sd,health,date_entree_stock)"+
            "VALUES (?,?,?,?)";
        try {
            PreparedStatement pst = cnx2.prepareStatement(sql);
            pst.setString(1, sd.getNomSD().toString());
            pst.setFloat(2, sd.getQuantiteSD());
            pst.setString(3, sd.getHealthSD().toString());
            pst.setDate(4, (Date) sd.getDateEntreeStock());
            
            pst.executeUpdate();
            System.out.println("Un autre produit est ajouté avec succès !");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }   
      
    public List<StockDivers> afficherStockDivers(){
    List<StockDivers> myList = new ArrayList<>();
    String sql ="SELECT * FROM stock_divers";
        try {
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                StockDivers sd = new StockDivers(); 
                sd.setIdSD(rs.getInt(1));
                sd.setNomSD(NomDivStock.valueOf(rs.getString("nom_sd")));
                sd.setQuantiteSD(rs.getFloat("quantite_sd"));
                sd.setHealthSD(Health.valueOf(rs.getString("health")));
                sd.setDateEntreeStock(rs.getDate("date_entree_stock"));
                myList.add(sd);      
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    return myList;
 } 
    
    public StockDivers chercherStockDivers(int id) {
    String sql = "SELECT * FROM stock_divers WHERE id=?";
    try {
        PreparedStatement pst = cnx2.prepareStatement(sql);
        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
                StockDivers sd = new StockDivers(); 
                sd.setIdSD(rs.getInt(1));
                sd.setNomSD(NomDivStock.valueOf(rs.getString("nom_sd")));
                sd.setQuantiteSD(rs.getFloat("quantite_sd"));
                sd.setHealthSD(Health.valueOf(rs.getString("health")));
                sd.setDateEntreeStock(rs.getDate("date_entree_stock"));
            return sd;
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return null;
}
       
    public void modifierStockDivers(StockDivers sd) {
    String sql = "UPDATE stock_divers SET nom_sd=?, quantite_sd=?,  health=?, date_entree_stock=? WHERE id=?";
    try {
            PreparedStatement pst = cnx2.prepareStatement(sql);
            pst.setString(1, sd.getNomSD().toString());
            pst.setFloat(2, sd.getQuantiteSD());
            pst.setString(3, sd.getHealthSD().toString());
            pst.setDate(4, (Date) sd.getDateEntreeStock());
            pst.setInt(5, sd.getIdSD());
            
        int rowsAffected = pst.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Le produit a été modifié avec succès !");
        } else {
            System.out.println("Aucune modification n'a été effectuée.");
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}
    
    public void supprimerStockDivers(int id) {
    String sql = "DELETE FROM stock_divers WHERE id=?";
    try {
        PreparedStatement pst = cnx2.prepareStatement(sql);
        pst.setInt(1, id);

        int rowsAffected = pst.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Le produit a été supprimé avec succès !");
        } else {
            System.out.println("Aucune suppression n'a été effectuée.");
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}
    
}
