/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.services;

import agrify.utils.MyConnection;
import agrify.entities.Healh;
import agrify.services.HealthInterfaces;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hadil sghair
 */
public class HealthCrud implements HealthInterfaces {
Connection cnx=MyConnection.getInstance().getCon();
    @Override
    public void ajoutermaladie(Healh h) {
         try {
            String req ="INSERT INTO `maladie`(`animal_id`, `medicament`, `type_de_traitement`, `dosage`) VALUES ('"+h.getAnimalId()+"','"+h.getMedicament()+"','"+h.getTypeDeTraitement()+"','"+h.getDosage()+"')";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
            System.out.println("animal malade ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifiermaladie(int id, Healh h) {
       String sql = "UPDATE `maladie` SET `animal_id`=?,`medicament`=?,`type_de_traitement`=?,`dosage`=? WHERE `id`=?";

        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, h.getAnimalId());
          
            ps.setString(2, h.getMedicament());
            ps.setString(3, h.getTypeDeTraitement());
            ps.setString(4, h.getDosage());
            
            ps.setInt(5, id);

            int rowsUpdated = ps.executeUpdate();
    
          //  if (rowsUpdated > 0) {
                System.out.println("Rec updated successfully");
           // } else {
           //   }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    
    }

    @Override
    public void supprimermaladie(int id) {
       String requet = "DELETE FROM `maladie` WHERE `id`=?";

        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(requet);
            preparedStatement.setInt(1, id); // Set the value for the parameter
            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Row with id " + id + " deleted successfully.");
            } else {
                System.out.println("No row with id " + id + " found in the database.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Healh> affichermaladie() {
       List <Healh> recs =new ArrayList<>();
        String requet="SELECT `id`, `animal_id`, `medicament`, `type_de_traitement`, `dosage` FROM `maladie` WHERE 1";
        Statement stm = null;
        try {
            stm = cnx.createStatement();
            ResultSet res =stm.executeQuery(requet);
            while (res.next()){
               Healh rp=new Healh();
               rp.setHealthid(res.getInt(1));
               rp.setAnimalId(res.getString(2));
               
               rp.setMedicament(res.getString(3));
               rp.setTypeDeTraitement(res.getString(4));
               rp.setDosage(res.getString(5));
              
                recs.add(rp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return recs;
    }
 
      public Healh rechercheMaladieParId(int id) {
    try {
        String req = "SELECT * FROM `maladie` WHERE id = " + id;
        Statement stm = cnx.createStatement();
        ResultSet res = stm.executeQuery(req);

        if (res.next()) {
             Healh rp=new Healh();
               rp.setHealthid(res.getInt("id"));
               rp.setAnimalId(res.getString("animal_id"));
            
               rp.setMedicament(res.getString("medicament"));
               rp.setTypeDeTraitement(res.getString("type_de_traitement"));
               rp.setDosage(res.getString("dosage"));
            
            return rp;
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    
    return null; // Renvoie null si la réclamation n'est pas trouvée.
}
   
}
