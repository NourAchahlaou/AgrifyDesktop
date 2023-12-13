/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.controllers;

/**
 *
 * @author tbagh
 */

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UserDashboardController {

    @FXML
    private Button GestionAnimalBtn;

    @FXML
    private Button GestionIngrediantsBtn;

    @FXML
    private Button GestionNutritionBtn;

    @FXML
    private Button GestionProduitBatn;

    @FXML
    private Button GestionTraitementBtn;

    @FXML
    private Button UserDashboardAccountSettingsBtn;

    @FXML
    private Button UserDashboardDeconnexionBtn;

    @FXML
    private Button UserDashboardGestionReclamationBtn;

    @FXML
    private Button UserDashboardGestionTachesBtn;

    @FXML
    void GestionAnimal(ActionEvent event) {

    }

    @FXML
    void GestionIngrediants(ActionEvent event) {

    }

    @FXML
    void GestionNutrition(ActionEvent event) {

    }

    @FXML
    void GestionProduit(ActionEvent event) {

    }

    @FXML
    void GestionTraitement(ActionEvent event) {

    }

    @FXML
    void UserDashboardAccountSettings(ActionEvent event) {

    }

    @FXML
    void UserDashboardDeconnexion(ActionEvent event) throws IOException {
        
        
                Parent animalDashboardRoot = FXMLLoader.load(getClass().getResource("/agrify/views/signin.fxml"));
                Scene animalDashboardScene = new Scene(animalDashboardRoot);

                Stage animalDashboardStage = new Stage();
                animalDashboardStage.initStyle(StageStyle.TRANSPARENT);
                animalDashboardStage.setScene(animalDashboardScene);
                animalDashboardStage.show();

                Stage signInStage = (Stage) UserDashboardDeconnexionBtn.getScene().getWindow();
                signInStage.close();
    }

    @FXML
    void UserDashboardGestionReclamation(ActionEvent event) {

    }

    @FXML
    void UserDashboardGestionTaches(ActionEvent event) {

    }

}
