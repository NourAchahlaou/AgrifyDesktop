/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.controllers;

import agrify.entities.Reclamation;
import agrify.entities.TypeRec;
import agrify.services.RecCrud;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Hadil sghair
 */
public class RecsuppController implements Initializable {
  @FXML
    private TextField Recsuppid;
    @FXML
    private Button recrecherche;
    @FXML
    private Button recsupp;
    @FXML
    private Label labelsupp;
    @FXML
    private TableView<Reclamation> tablerecrech;
    @FXML
    private TableColumn<Reclamation, Integer> rs1;
    @FXML
    private TableColumn<Reclamation, String> rs2;
    @FXML
    private TableColumn<Reclamation, String> rs3;
    @FXML
    private TableColumn<Reclamation, String> rs4;
    @FXML
    private TableColumn<Reclamation, String> rs5;
    @FXML
    private TableColumn<Reclamation, String> rs7;
    @FXML
    private TableColumn<Reclamation, String> rs8;
    @FXML
    private Button ReturnSupprec2;
    @FXML
    private AnchorPane ll;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rs1.setCellValueFactory(new PropertyValueFactory<>("rec_id")); // Replace "rec_id" with the actual property in Reclamation
    rs2.setCellValueFactory(new PropertyValueFactory<>("rec_emp")); // Replace "rec_emp" with the actual property in Reclamation
    rs3.setCellValueFactory(new PropertyValueFactory<>("rec_date")); // Replace "rec_date" with the actual property in Reclamation
rs4.setCellValueFactory(cellData -> {
    Reclamation reclamation = cellData.getValue();
    TypeRec typeRec = reclamation.getTypeReclamation(); // Assuming this method gets the associated TypeRec object
    
    if (typeRec != null) {
        return new SimpleStringProperty(typeRec.getType()); // Display type information in the table
    } else {
        return new SimpleStringProperty(""); // Or handle the case where there's no associated TypeRec object
    }
});    rs5.setCellValueFactory(new PropertyValueFactory<>("rec_description")); // Replace "rec_description" with the actual property in Reclamation
    rs7.setCellValueFactory(new PropertyValueFactory<>("rec_target")); // Replace "rec_target" with the actual property in Reclamation
    rs8.setCellValueFactory(new PropertyValueFactory<>("urgency"));
    }    
    
    @FXML
    private void onrecrecherche(ActionEvent event) {
        int search;
        search = Integer.parseInt(Recsuppid.getText());
        RecCrud rp=new RecCrud();
        
        Reclamation c=new Reclamation();
       c=rp.rechercheReclamationParId(search);
         ObservableList<Reclamation> observableRecs = FXCollections.observableArrayList(c);
         tablerecrech.setItems(observableRecs);
        
    }

    @FXML
    private void onrecsupp(ActionEvent event) {
       String text = Recsuppid.getText(); 
       int searchTerm = Integer.parseInt(text);
       RecCrud cp=new RecCrud();
       cp.supprimerreclamation(searchTerm);
         labelsupp.setText("Reclamation supprimé");
    }

    @FXML
    private void onReturnSupprec2(ActionEvent event) {
        
      try {
        Parent rr8Root = FXMLLoader.load(getClass().getResource("/agrify/views/RecAffichage.fxml"));
        Scene Scene1 = new Scene(rr8Root);
        
        
        // Create a new stage  interface
        Stage er18Stage = new Stage();
        er18Stage.initStyle(StageStyle.TRANSPARENT);
        er18Stage.setScene(Scene1);
        er18Stage.show();
        
        // Close the splash screen stage
        Stage splash3 = (Stage) ReturnSupprec2.getScene().getWindow();
        splash3.close();
    } catch (IOException ex) {
        Logger.getLogger(RecAffichageController.class.getName()).log(Level.SEVERE, null, ex);
    }  
    }
    
}
