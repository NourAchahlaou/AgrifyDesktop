package agrify.controllers;

import agrify.entities.Field;
import agrify.services.ServiceField;
import agrify.utils.DataSource;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author tbagh
 */


public class FieldAjoutController {

    @FXML
    private Button AddFieldBackBtn1;

    @FXML
    private Button AddFieldBtn;

    @FXML
    private Label AddUserMessage;

    @FXML
    private TextField FieldNomTextFill;

    @FXML
    private TextField FieldQuantitéTextFill;

    @FXML
    private TextField FieldSuperficieTextFill;

    @FXML
    private TextField FieldTypeTextFill;
    
    
    

    @FXML
    void AddField(ActionEvent event) throws IOException 
    
    {
        ServiceField fieldService = new ServiceField(DataSource.getInstance().getConnection());
    
        String fieldNom = FieldNomTextFill.getText();
        String fieldType = FieldTypeTextFill.getText();
        double fieldSuperficie = Double.parseDouble(FieldSuperficieTextFill.getText());
        int fieldQuantity = Integer.parseInt(FieldQuantitéTextFill.getText());

         Field field = new Field(fieldNom, fieldType, fieldSuperficie, fieldQuantity);
         fieldService.ajouter(field);
      
            Parent signUpRoot = FXMLLoader.load(getClass().getResource("/agrify/views/FiledHome.fxml"));
            Scene signUpScene = new Scene(signUpRoot);
   
            Stage signUpStage = new Stage();
            signUpStage.initStyle(StageStyle.TRANSPARENT);
            signUpStage.setScene(signUpScene);
            signUpStage.show();

            Stage splashSignInStage = (Stage) AddFieldBtn.getScene().getWindow();
            splashSignInStage.close();
    }
    
    
    
    

    @FXML
    void AddFieldBack(ActionEvent event) throws IOException
    {
        Parent signUpRoot = FXMLLoader.load(getClass().getResource("/agrify/views/FiledHome.fxml"));
        Scene signUpScene = new Scene(signUpRoot);
   
        Stage signUpStage = new Stage();
        signUpStage.initStyle(StageStyle.TRANSPARENT);
        signUpStage.setScene(signUpScene);
        signUpStage.show();

        Stage splashSignInStage = (Stage) AddFieldBackBtn1.getScene().getWindow();
        splashSignInStage.close();
    }

}
