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
import java.util.List;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author chaim
 */
public class AffichageMaladieController implements Initializable {

   @FXML
    private TableColumn<Healh, String> maladieaff1;
    @FXML
    private TableColumn<Healh, String> maladieaff2;
  
    @FXML
    private TableColumn<Healh, String> maladieaff4;
    @FXML
    private TableColumn<Healh, String> maladieaff5;
    @FXML
    private TableColumn<Healh, String> maladieaff6;
    @FXML
    private TableView tablemalaaff;
    @FXML
    private Button allerajoutermaladie;
    @FXML
    private Button allermaladieReclamation;
    @FXML
    private Button historiqueanimal;
    @FXML
    private Button allerasuppermaladie;
    @FXML
    private ImageView exitmaladie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        maladieaff1.setCellValueFactory(new PropertyValueFactory<>("healthid")); 
    maladieaff2.setCellValueFactory(new PropertyValueFactory<>("animalId")); 
   
    maladieaff4.setCellValueFactory(new PropertyValueFactory<>("medicament")); 
    maladieaff5.setCellValueFactory(new PropertyValueFactory<>("typeDeTraitement")); 
    maladieaff6.setCellValueFactory(new PropertyValueFactory<>("dosage")); 
    

  
        HealthCrud rp=new HealthCrud();
        List<Healh> maladies = rp.affichermaladie();

   
    ObservableList<Healh> observableRecs = FXCollections.observableArrayList(maladies);

    tablemalaaff.setItems(observableRecs);
    }    

    @FXML
    private void onallerajoutermaladie(ActionEvent event) {
        try {
        Parent aff12Root = FXMLLoader.load(getClass().getResource("/agrify/views/AjouterMaladie.fxml"));
        Scene Scene1 = new Scene(aff12Root);
        
        
        // Create a new stage  interface
        Stage ret12Stage = new Stage();
        ret12Stage.initStyle(StageStyle.TRANSPARENT);
        ret12Stage.setScene(Scene1);
        ret12Stage.show();
        
        // Close the splash screen stage
        Stage splash2 = (Stage) allerajoutermaladie.getScene().getWindow();
        splash2.close();
    } catch (IOException ex) {
            System.out.println("1");
    }
    }

    @FXML
    private void onallermaladieReclamation(ActionEvent event) {
         try {
        Parent aff123Root = FXMLLoader.load(getClass().getResource("/agrify/views/RecAffichage.fxml"));
        Scene Scene1 = new Scene(aff123Root);
        
        
        // Create a new stage  interface
        Stage ret123Stage = new Stage();
        ret123Stage.initStyle(StageStyle.TRANSPARENT);
        ret123Stage.setScene(Scene1);
        ret123Stage.show();
        
        // Close the splash screen stage
        Stage splash1b2 = (Stage) allermaladieReclamation.getScene().getWindow();
        splash1b2.close();
    } catch (IOException ex) {
            System.out.println("1");
    }
    }

    @FXML
    private void onhistoriqueanimal(ActionEvent event) {
          try {
        Parent aff123Root = FXMLLoader.load(getClass().getResource("/agrify/views/ModifierMaladie.fxml"));
        Scene Scene1 = new Scene(aff123Root);
        
        
        // Create a new stage  interface
        Stage ret123Stage = new Stage();
        ret123Stage.initStyle(StageStyle.TRANSPARENT);
        ret123Stage.setScene(Scene1);
        ret123Stage.show();
        
        // Close the splash screen stage
        Stage splash1b2 = (Stage) historiqueanimal.getScene().getWindow();
        splash1b2.close();
    } catch (IOException ex) {
            System.out.println("1");
    }
    }

    @FXML
    private void onallerasuppermaladie(ActionEvent event) {
                try {
        Parent aff123Root = FXMLLoader.load(getClass().getResource("/agrify/views/SupprimerMaladie.fxml"));
        Scene Scene1 = new Scene(aff123Root);
        
        
        // Create a new stage  interface
        Stage ret123Stage = new Stage();
        ret123Stage.initStyle(StageStyle.TRANSPARENT);
        ret123Stage.setScene(Scene1);
        ret123Stage.show();
        
        // Close the splash screen stage
        Stage splash1b2 = (Stage) allerasuppermaladie.getScene().getWindow();
        splash1b2.close();
    } catch (IOException ex) {
            System.out.println("1");
    }
    }

    @FXML
    private void onexitmaladie(MouseEvent event) {
        javafx.application.Platform.exit();
    }
}
