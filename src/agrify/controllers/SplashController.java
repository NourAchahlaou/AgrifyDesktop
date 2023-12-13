package agrify.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



/**
 *
 * @author tbagh
 */



public class SplashController {

      @FXML
    private Button HomequitterBtn;

    @FXML
    private Button HomeLoginBtn;

    @FXML
    private Button HomeRegisterBtn;
    
    

@FXML
void HomeLogin(ActionEvent event) throws Exception

 {
   Parent signUpRoot = FXMLLoader.load(getClass().getResource("/agrify/views/signin.fxml"));
   Scene signUpScene = new Scene(signUpRoot);
   
   Stage signUpStage = new Stage();
   signUpStage.initStyle(StageStyle.TRANSPARENT);
   signUpStage.setScene(signUpScene);
   signUpStage.show();

   Stage splashSignInStage = (Stage) HomeLoginBtn.getScene().getWindow();
   splashSignInStage.close();
 }
    
@FXML
  void Homequitter(ActionEvent event) 
    {
      System.exit(0);
    }

}
