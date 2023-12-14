/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.services;


import agrify.utils.MyConnection;
import agrify.entities.Reclamation;
import agrify.entities.TypeRec;
import agrify.services.Recinterfaces;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecCrud  implements Recinterfaces{
  Connection cnx=MyConnection.getInstance().getCon();
    @Override
    public void ajouterreclamation(Reclamation rec) {
        try {
              Connection cnx=MyConnection.getInstance().getCon();
            String req =" INSERT INTO `reclamation`(`type_rec_id`, `rec_emp`, `rec_date`, `rec_description`, `rec_target`, `urgency`) VALUES" +
                    " ('"+rec.getTypeReclamation().getType()+"','"+rec.getRec_emp()+"','"+rec.getRec_date()+"','"+rec.getRec_description()+"','"+rec.getRec_target()+"','"+rec.getUrgency()+"')";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
            System.out.println("reclamtion ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    

    @Override
    public void supprimerreclamation(int id) {
        String requet = "DELETE FROM `reclamation` WHERE `id`=?";

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
public List<Reclamation> afficherreclamation() {
    List<Reclamation> recs = new ArrayList<>();
    String requet = "SELECT `id`, `type_rec_id`, `rec_emp`, `rec_date`, `rec_description`, `rec_target`, `urgency` FROM `reclamation` WHERE 1";

    try (Statement stm = cnx.createStatement()) {
        ResultSet res = stm.executeQuery(requet);
        while (res.next()) {
            Reclamation rp = new Reclamation();
            rp.setRec_id(res.getInt(1));
            rp.setRec_emp(res.getString(3));
            rp.setRec_date(res.getDate(4));
            
            // Fetch TypeReclamation from the ID
            TypeRec typeRec = getTypeReclamationById(res.getInt(2));
            rp.setTypeReclamation(typeRec);
            
            rp.setRec_description(res.getString(5));
            rp.setRec_target(res.getString(6));
            rp.setUrgency(res.getString(7));
            recs.add(rp);
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    return recs;
}


private TypeRec getTypeReclamationById(int typeId) {
    TypeRec typeRec = null;
    String query = "SELECT `id`, `type`, `description` FROM `typede_reclamation` WHERE `id` = ?";
    
    try (PreparedStatement ps = cnx.prepareStatement(query)) {
        ps.setInt(1, typeId);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            typeRec = new TypeRec();
            typeRec.setId(resultSet.getInt("id"));
            typeRec.setType(resultSet.getString("type"));
            typeRec.setDescription(resultSet.getString("description"));
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    
    return typeRec;
}
    
    @Override
    public void modifierreclamation(int id,Reclamation rec) {
        String sql = "UPDATE `reclamation` SET `type_rec_id`=?, `rec_emp`=?, `rec_date`=?, `rec_description`=?, `rec_target`=?, `urgency`=? WHERE `id`=?";

        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
           ps.setString(1, rec.getTypeReclamation().getType()); // Assuming type_rec_id is an integer in Reclamation class
        ps.setString(2, rec.getRec_emp());
        ps.setDate(3, new java.sql.Date(rec.getRec_date().getTime())); // Assuming rec_date is a java.util.Date in Reclamation class
        ps.setString(4, rec.getRec_description());
        ps.setString(5, rec.getRec_target());
        ps.setString(6, rec.getUrgency());
        ps.setInt(7, rec.getRec_id());

            int rowsUpdated = ps.executeUpdate();
    
          //  if (rowsUpdated > 0) {
                System.out.println("Rec updated successfully");
           // } else {
           //   }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    
    }
 /* @Override
    public Reclamation rechercheReclamationParId(int id) {
    try {
        String req = "SELECT * FROM reclamation WHERE id = " + id;
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(req);

        if (rs.next()) {
            Reclamation rec = new Reclamation();
            rec.setRec_id(rs.getInt("rec_id"));
            rec.setRec_emp(rs.getString("rec_emp"));
            rec.setRec_date(rs.getString("rec_date"));
            rec.setTypeReclamation(rs.getString("typeReclamation"));
            rec.setRec_description(rs.getString("rec_description"));
            rec.setRec_id_anim_plante(rs.getString("rec_id_anim_plante"));
            rec.setRec_target(rs.getString("rec_target"));
            rec.setUrgency(rs.getString("urgency"));
            
            return rec;
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    
    return null; // Renvoie null si la réclamation n'est pas trouvée.
}*/
    public void viderunetable() {
        String req="TRUNCATE TABLE `rec1`.`reclamation`";
         try {
            PreparedStatement preparedStatement = cnx.prepareStatement(req);
             preparedStatement.executeUpdate();

            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Reclamation rechercheReclamationParId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   

}
