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

import agrify.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UserModifierNotif {

    @FXML
    private Button ModifierNotifBackBtn;

    @FXML
    private Button ModifierNotifConfirmBtn;
    
    private boolean confirmation = false;
        private User userData;

        
        
     public void setUserData(User user) {
        userData = user;
    }


    @FXML
    void ModifierNotifBack(ActionEvent event) throws Exception
   {

        confirmation = false;
        Stage stage = (Stage) ModifierNotifBackBtn.getScene().getWindow();
        stage.close();
   }

    @FXML
    void ModifierNotifConfirm(ActionEvent event) throws Exception
   {

        confirmation = true; 
        Stage stage = (Stage) ModifierNotifConfirmBtn.getScene().getWindow();
        stage.close();
   }

    
     public boolean getConfirmation() {
        return confirmation;
    }
     
     
}
