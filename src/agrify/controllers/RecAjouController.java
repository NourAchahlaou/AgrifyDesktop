/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.controllers;

import agrify.entities.Reclamation;
import agrify.entities.TypeRec;
import agrify.services.RecCrud;
import agrify.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hadil sghair
 */
public class RecAjouController implements Initializable {

   @FXML
    private Button recajreturn;
    @FXML
    private TextField recaj1;
    @FXML
    private TextField recaj2;
    @FXML
    private ComboBox recaj3;
    @FXML
    private TextField recaj4;
 
    @FXML
    private ComboBox recaj6;
    @FXML
    private RadioButton recaj1radio1;
    @FXML
    private RadioButton recaj1radio2;
    @FXML
    private Button recaj;
    @FXML
    private Button recclearaj;
    @FXML
    private Label labmsg;
    @FXML
    private ToggleGroup urgence;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
         ObservableList<String> list2 = FXCollections.observableArrayList("Chef d exécution d éleveurs","Chef d exécution des agricoles","manageur de Stock");
         recaj6.setItems(list2);
        populateTypeReclamationComboBox();

        
    }  
    private void populateTypeReclamationComboBox() {
    List<TypeRec> allTypeRecs = getAllTypeReclamations(); // Fetch all TypeRec objects from the database

    if (!allTypeRecs.isEmpty()) {
        ObservableList<TypeRec> observableTypeRec = FXCollections.observableArrayList(allTypeRecs);
        recaj3.setItems(observableTypeRec);

        recaj3.setCellFactory(new Callback<ListView<TypeRec>, ListCell<TypeRec>>() {
            @Override
            public ListCell<TypeRec> call(ListView<TypeRec> param) {
                return new ListCell<TypeRec>() {
                    @Override
                    protected void updateItem(TypeRec item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(item.getType()); // Assuming getType() returns the type information
                        }
                    }
                };
            }
        });
    }
}

private List<TypeRec> getAllTypeReclamations() {
    List<TypeRec> allTypeRecs = new ArrayList<>();
    String query = "SELECT `id`, `type`, `description` FROM `typede_reclamation`";
    
    try (Connection cnx = MyConnection.getInstance().getCon();
         PreparedStatement ps = cnx.prepareStatement(query);
         ResultSet resultSet = ps.executeQuery()) {
        
        while (resultSet.next()) {
            TypeRec typeRec = new TypeRec();
            typeRec.setId(resultSet.getInt("id"));
            typeRec.setType(resultSet.getString("type"));
            typeRec.setDescription(resultSet.getString("description"));
            allTypeRecs.add(typeRec);
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    
    return allTypeRecs;
}


    @FXML
    private void returnajo(ActionEvent event) {
        try {
        Parent rr8Root = FXMLLoader.load(getClass().getResource("/agrify/views/RecAffichage.fxml"));
        Scene Scene1 = new Scene(rr8Root);
        
        
        // Create a new stage  interface
        Stage er18Stage = new Stage();
        er18Stage.initStyle(StageStyle.TRANSPARENT);
        er18Stage.setScene(Scene1);
        er18Stage.show();
        
        // Close the splash screen stage
        Stage splash3 = (Stage) recajreturn.getScene().getWindow();
        splash3.close();
    } catch (IOException ex) {
        Logger.getLogger(RecAffichageController.class.getName()).log(Level.SEVERE, null, ex);
    }  
    

    
    
    }

    @FXML
    private void saverec(ActionEvent event) throws ParseException {
              
       
      String a1= recaj1.getText();
        Date a2 = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Define your date format
        a2 = dateFormat.parse(recaj2.getText());
       TypeRec s = (TypeRec) recaj3.getSelectionModel().getSelectedItem();

        String a4= recaj4.getText();
       
        String s6 = recaj6.getSelectionModel().getSelectedItem().toString();
        String b = "" ;
        if(recaj1radio1.isSelected()) {
               b="urgent";
              System.out.println("Radio 1 Selected: ");
  }
        else if(recaj1radio1.isSelected()) {
                b="Not urgent";
          
         }else {
                    b = "Not urgent"; 
}

 
   
          
        if (a4.isEmpty()) {
        showAlert("Le champ description est requis.");
        return;
    }
        

         showAlertIfValueIsNull(recaj3);
         showAlertIfValueIsNull(recaj6);


                                         
   
                        
       Reclamation r1 =new Reclamation(a1,a2,s,a4,s6,b);
       RecCrud lp=new RecCrud();
      lp.ajouterreclamation(r1);
        labmsg.setText("Reclamation Ajouté"); 
}
  


    

    @FXML
    private void clearval(ActionEvent event) {
            recaj1.clear();
            recaj2.clear();
            recaj4.clear();
         
            recaj3.getSelectionModel().clearSelection();
            recaj6.getSelectionModel().clearSelection();
            recaj1radio1.setSelected(false);
            recaj1radio2.setSelected(false);
    }

    @FXML
    private void geturgence(ActionEvent event) {
    }
  
            
      
         
     private void showAlert(String message) {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Erreur de saisie");
          alert.setHeaderText(null);
          alert.setContentText(message);
          alert.showAndWait();
}    
      public static void showAlertIfValueIsNull(ComboBox<String> comboBox) {
        if (comboBox.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a valid option from the ComboBox.");
            alert.showAndWait();
        }
    }
        
    }