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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Hadil sghair
 */
public class ModifierMaladieController implements Initializable {

       @FXML
    private TextField maladiemodif1;
 
    @FXML
    private TextField maladiemodif3;
    @FXML
    private TextField maladiemodif4;
    @FXML
    private RadioButton modifr1;
    @FXML
    private RadioButton modifr2;
    @FXML
    private Button donnerdatamodif;
    @FXML
    private Button modifiermaladie;
    @FXML
    private Button retuenmaladiemodif;
    @FXML
    private Label msgModofmaladie;
    @FXML
    private TextField maladiemodifid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    }

    @FXML
    private void ondonnerdatamodif(ActionEvent event) {
    }

    @FXML
    private void onmodifiermaladie(ActionEvent event) {
           int id= Integer.parseInt(maladiemodifid.getText()) ;
        String a1= maladiemodif1.getText();
       
       
         String a4= maladiemodif3.getText();
        String a5= maladiemodif4.getText();
        
          String b = "" ;
         if(modifr1.isSelected()) {
               b="vaccination";
              
  }
          else if(modifr2.isSelected()) {
                b="medicament a avaler";
          
         } else {
                    b = "pas spécifier "; 
}
                          
       Healh h=new Healh (a1,a4,a5,b);
        HealthCrud hp=new HealthCrud();
         hp.modifiermaladie(id, h);
       msgModofmaladie.setText("Maladie Modifier"); 
    } 
    

    

@FXML
    private void onretuenmaladiemodif(ActionEvent event) {
      
                try {
        Parent aff12Root = FXMLLoader.load(getClass().getResource("/agrify/views/AffichageMaladie.fxml"));
        Scene Scene1 = new Scene(aff12Root);
        
        
        // Create a new stage  interface
        Stage ret12Stage = new Stage();
        ret12Stage.initStyle(StageStyle.TRANSPARENT);
        ret12Stage.setScene(Scene1);
        ret12Stage.show();
        
        // Close the splash screen stage
        Stage splash2 = (Stage) retuenmaladiemodif.getScene().getWindow();
        splash2.close();
    } catch (IOException ex) {
            System.out.println("1");
    }
    }
}