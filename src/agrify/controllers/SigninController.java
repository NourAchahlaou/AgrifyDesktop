package agrify.controllers;

import agrify.entities.User;
import agrify.services.ServiceUser;
import agrify.utils.DataSource;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author tbagh
 */



public class SigninController {

    @FXML
    private Button HomeBackBtn;

    @FXML
    private Button SgininBtn0;

    @FXML
    private TextField SgininUsername;

    @FXML
    private PasswordField Signinpsswd;
    
    @FXML
    private Label SigninMessage;

    @FXML
    private Label SigninUsernameMessage;


    
    
@FXML
 void HomeBack(ActionEvent event)throws Exception
         
    {
        Parent signUpRoot = FXMLLoader.load(getClass().getResource("/agrify/views/Home.fxml"));
        Scene signUpScene = new Scene(signUpRoot);

        Stage signUpStage = new Stage();
        signUpStage.initStyle(StageStyle.TRANSPARENT);
        signUpStage.setScene(signUpScene);
        signUpStage.show();

        Stage splashSignInStage = (Stage) HomeBackBtn.getScene().getWindow();
        splashSignInStage.close();
    }



@FXML
void Sginin0(ActionEvent event) throws IOException {
     ServiceUser userService = new ServiceUser(DataSource.getInstance().getConnection());
    String username = SgininUsername.getText();
    String password = Signinpsswd.getText();
    
        System.out.println("Username: " + username); // Log the username
    System.out.println("Password: " + password); // Log the password


     if (username.isEmpty() || password.isEmpty()) 
     {
        SigninMessage.setText("Veuillez saisir le nom d'utilisateur et le mot de passe.");
     } 
     else 
     {
        User authenticatedUser = userService.authenticateUser(username, password);
        if (authenticatedUser == null) 
        {
            SigninMessage.setText("Informations de connexion incorrectes.");
   
        } 
        else 
        {
             SigninMessage.setText("Informations de connexion correct.");
            String role = authenticatedUser.getUser_role();
                    System.out.println("Role: " + role); 

            if ("Admin".equals(role)) {
                Parent adminDashboardRoot = FXMLLoader.load(getClass().getResource("/agrify/views/AdminDashboard.fxml"));
                Scene adminDashboardScene = new Scene(adminDashboardRoot);

                Stage adminDashboardStage = new Stage();
                adminDashboardStage.initStyle(StageStyle.TRANSPARENT);
                adminDashboardStage.setScene(adminDashboardScene);
                adminDashboardStage.show();

                Stage signInStage = (Stage) SgininBtn0.getScene().getWindow();
                signInStage.close();
                
                
            } 
            else if ("User".equals(role)) {
                Parent animalDashboardRoot = FXMLLoader.load(getClass().getResource("/agrify/views/UserDashboard.fxml"));
                Scene animalDashboardScene = new Scene(animalDashboardRoot);

                Stage animalDashboardStage = new Stage();
                animalDashboardStage.initStyle(StageStyle.TRANSPARENT);
                animalDashboardStage.setScene(animalDashboardScene);
                animalDashboardStage.show();

                Stage signInStage = (Stage) SgininBtn0.getScene().getWindow();
                signInStage.close();
            }
        }
    }
}



}
