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
import java.util.List;
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
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Hadil sghair
 */
public class RecAffichageController implements Initializable {
@FXML
private TableView<Reclamation> table;
@FXML
private TableColumn<Reclamation, Integer> Reccul11;
@FXML
private TableColumn<Reclamation, String> Reccul1e;
@FXML
private TableColumn<Reclamation, String> Reccul2d;
@FXML
private TableColumn<Reclamation, String> Reccul3t;
@FXML
private TableColumn<Reclamation, String> Reccul4d;

@FXML
private TableColumn<Reclamation, String> Reccul14c;
@FXML
private TableColumn<Reclamation, String> Reccul15u;
    @FXML
    private Button allermodiftrec;
    @FXML
    private Button allerajoutrec;
    @FXML
    private Button RECMODIFALLER;
    @FXML
    private Button cleartabrecaff;
    @FXML
    private ImageView exitpointrec;

    /**
     * Initializes the controller class.
     */
  
   @Override
public void initialize(URL location, ResourceBundle resources) {
    // Configurez les cellules de chaque colonne pour afficher les données
 Reccul11.setCellValueFactory(new PropertyValueFactory<>("rec_id")); // Replace "rec_id" with the actual property in Reclamation
    Reccul1e.setCellValueFactory(new PropertyValueFactory<>("rec_emp")); // Replace "rec_emp" with the actual property in Reclamation
    Reccul2d.setCellValueFactory(new PropertyValueFactory<>("rec_date")); // Replace "rec_date" with the actual property in Reclamation
   Reccul3t.setCellValueFactory(cellData -> {
    Reclamation reclamation = cellData.getValue();
    TypeRec typeRec = reclamation.getTypeReclamation(); // Assuming this method gets the associated TypeRec object
    
    if (typeRec != null) {
        return new SimpleStringProperty(typeRec.getType()); // Display type information in the table
    } else {
        return new SimpleStringProperty(""); // Or handle the case where there's no associated TypeRec object
    }
});
    Reccul4d.setCellValueFactory(new PropertyValueFactory<>("rec_description")); // Replace "rec_description" with the actual property in Reclamation
    
    Reccul14c.setCellValueFactory(new PropertyValueFactory<>("rec_target")); // Replace "rec_target" with the actual property in Reclamation
    Reccul15u.setCellValueFactory(new PropertyValueFactory<>("urgency"));

  
     RecCrud rp=new RecCrud();
        List<Reclamation> recs = rp.afficherreclamation();

   
    ObservableList<Reclamation> observableRecs = FXCollections.observableArrayList(recs);

    table.setItems(observableRecs);
}


    @FXML
    private void onallermodiftrec(ActionEvent event) {
    try {
        Parent modifRoot = FXMLLoader.load(getClass().getResource("/agrify/views/RecModif.fxml"));
        Scene signUpScene = new Scene(modifRoot);
        
        
        // Create a new stage  interface
        Stage modifStage = new Stage();
        modifStage.initStyle(StageStyle.TRANSPARENT);
        modifStage.setScene(signUpScene);
        modifStage.show();
        
        // Close the splash screen stage
        Stage splash1 = (Stage) allermodiftrec.getScene().getWindow();
        splash1.close();
    } catch (IOException ex) {
        Logger.getLogger(RecAffichageController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @FXML
    private void onallerajoutrec(ActionEvent event) {
        
         try {
        Parent ajoutRoot = FXMLLoader.load(getClass().getResource("/agrify/views/RecAjou.fxml"));
        Scene signUpScene = new Scene(ajoutRoot);
        
        
        // Create a new stage  interface
        Stage ajouStage = new Stage();
        ajouStage.initStyle(StageStyle.TRANSPARENT);
        ajouStage.setScene(signUpScene);
        ajouStage.show();
        
        // Close the splash screen stage
        Stage splash2 = (Stage) allerajoutrec.getScene().getWindow();
        splash2.close();
    } catch (IOException ex) {
        Logger.getLogger(RecAffichageController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @FXML
    private void onRECMODIFALLER(ActionEvent event) {
        //****************supprimer faute de declaration !!!!!!!!!!!!!!!!!!!!!!!!
       
   try {
        Parent aff12Root = FXMLLoader.load(getClass().getResource("/agrify/views/Recsupp.fxml"));
        Scene Scene1 = new Scene(aff12Root);
        
        
        // Create a new stage  interface
        Stage ret12Stage = new Stage();
        ret12Stage.initStyle(StageStyle.TRANSPARENT);
        ret12Stage.setScene(Scene1);
        ret12Stage.show();
        
        // Close the splash screen stage
        Stage splash2 = (Stage) RECMODIFALLER.getScene().getWindow();
        splash2.close();
    } catch (IOException ex) {
            System.out.println("1");
    }

    }



    @FXML
    private void oncleartabrecaff(ActionEvent event) {
        RecCrud cp=new RecCrud();
        cp.viderunetable();
        try {
        Parent supp1Root = FXMLLoader.load(getClass().getResource("/agrify/views/RecAffichage.fxml"));
        Scene signUpScene = new Scene(supp1Root);
        
        
        // Create a new stage  interface
        Stage suppfStage = new Stage();
        suppfStage.initStyle(StageStyle.TRANSPARENT);
        suppfStage.setScene(signUpScene);
        suppfStage.show();
        
        // Close the splash screen stage
        Stage splash4 = (Stage) RECMODIFALLER.getScene().getWindow();
        splash4.close();
    } catch (IOException ex) {
        Logger.getLogger(RecAffichageController.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    }

    @FXML
    private void table(SortEvent<String> event) {
    }

    @FXML
    private void onexitrec1(MouseEvent event) {
        javafx.application.Platform.exit();
       
    }
}