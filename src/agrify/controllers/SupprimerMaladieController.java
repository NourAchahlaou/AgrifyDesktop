/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.controllers;

import agrify.entities.Healh;
import agrify.services.HealthCrud;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Hadil sghair
 */
public class SupprimerMaladieController implements Initializable {

    @FXML
    private TextField maladieid1arech;
    @FXML
    private Button maladierecherche;
    @FXML
    private Button maladiesupp;
    @FXML
    private Label msgsuppmaladie;
    @FXML
    private TableColumn<Healh, String> suptab1;
    @FXML
    private TableColumn<Healh, String> suptab2;
    @FXML
    private TableColumn<Healh, String> suptab3;
    @FXML
    private TableColumn<Healh, String> suptab4;
    @FXML
    private TableColumn<Healh, String> suptab5;
    @FXML
    private TableColumn<Healh, String> suptab16;
    @FXML
    private Button returnMaladiesupp;
    @FXML
    private TableView tablemaldierech;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           suptab1.setCellValueFactory(new PropertyValueFactory<>("healthid")); 
    suptab2.setCellValueFactory(new PropertyValueFactory<>("animalId")); 
    suptab3.setCellValueFactory(new PropertyValueFactory<>("typeDeMaladie")); 
    suptab4.setCellValueFactory(new PropertyValueFactory<>("medicament")); 
    suptab5.setCellValueFactory(new PropertyValueFactory<>("typeDeTraitement")); 
    suptab16.setCellValueFactory(new PropertyValueFactory<>("dosage"));  
    
    }    

    @FXML
    private void onmaladierecherche(ActionEvent event) {
        int search;
        search = Integer.parseInt(maladieid1arech.getText());
       HealthCrud rp=new HealthCrud();
        
         Healh l=new Healh();
       l=rp.rechercheMaladieParId(search);
         ObservableList<Healh> observableRecs = FXCollections.observableArrayList(l);
         tablemaldierech.setItems(observableRecs);
    }

    @FXML
    private void onmaladiesupp(ActionEvent event) {
         String text = maladieid1arech.getText(); 
       int searchTerm = Integer.parseInt(text);
            HealthCrud rp=new HealthCrud();
       rp.supprimermaladie(searchTerm);
         msgsuppmaladie.setText("Maladie supprimé");
    }

    @FXML
    private void onreturnMaladiesupp(ActionEvent event) {
        try {
        Parent aff12Root = FXMLLoader.load(getClass().getResource("/agrify/views/AffichageMaladie.fxml"));
        Scene Scene1 = new Scene(aff12Root);
        
        
        // Create a new stage  interface
        Stage ret12Stage = new Stage();
        ret12Stage.initStyle(StageStyle.TRANSPARENT);
        ret12Stage.setScene(Scene1);
        ret12Stage.show();
        
        // Close the splash screen stage
        Stage splash2 = (Stage) returnMaladiesupp.getScene().getWindow();
        splash2.close();
    } catch (IOException ex) {
            System.out.println("1");
    }
    }
    
}
