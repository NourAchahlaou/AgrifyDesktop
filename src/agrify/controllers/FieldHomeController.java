package agrify.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import agrify.entities.Field;
import agrify.services.ServiceField;
import agrify.utils.DataSource;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 *
 * @author tbagh
 */


public class FieldHomeController {

    @FXML
    private Button AddFieldBtn;

    @FXML
    private Button DeleteFieldBtn;

    @FXML
    private Label EditUserMessage11;

    @FXML
    private TableView<Field> FieldHome; 
    @FXML
    private TableColumn<Field, Integer> FieldHome_id; 
    @FXML
    private TableColumn<Field, String> FieldHome_nom;
    @FXML
    private TableColumn<Field, Integer> FieldHome_quantité;
    @FXML
    private TableColumn<Field, Double> FieldHome_superficie;
    @FXML
    private TableColumn<Field, String> FieldHome_type;

    @FXML
    private Button ModifyFieldBtn;

    @FXML
    private Button SearchFieldBtn;

    @FXML
    private TextField SearchFieldTextFieldBtn;

    
    @FXML
    private Button userFieldBackBtn;
    private ServiceField serviceField; // Initialize the ServiceField
    private ObservableList<Field> fieldsList;

    
 @FXML
void initialize() 

{
    serviceField = new ServiceField(DataSource.getInstance().getConnection());
    fieldsList = FXCollections.observableArrayList();
    
    FieldHome_id.setCellValueFactory(new PropertyValueFactory<>("field_Id"));
    FieldHome_nom.setCellValueFactory(new PropertyValueFactory<>("field_Nom"));
    FieldHome_quantité.setCellValueFactory(new PropertyValueFactory<>("field_quantity"));
    FieldHome_superficie.setCellValueFactory(new PropertyValueFactory<>("field_Superficie"));
    FieldHome_type.setCellValueFactory(new PropertyValueFactory<>("field_type"));
    FieldHome.setItems(fieldsList);

    loadFieldData();
}



private void loadFieldData() 

{
    List<Field> fields = serviceField.getAll();
    fieldsList.addAll(fields);
}

    
@FXML
void AddField(ActionEvent event) throws IOException

{
   Parent signUpRoot = FXMLLoader.load(getClass().getResource("/agrify/views/FiledAdd.fxml"));
   Scene signUpScene = new Scene(signUpRoot);
   
   Stage signUpStage = new Stage();
   signUpStage.initStyle(StageStyle.TRANSPARENT);
   signUpStage.setScene(signUpScene);
   signUpStage.show();

   Stage splashSignInStage = (Stage) AddFieldBtn.getScene().getWindow();
   splashSignInStage.close();
}
    
    
    
    
@FXML
void DeleteField(ActionEvent event)  throws IOException 
    
{
   Parent signUpRoot = FXMLLoader.load(getClass().getResource("/agrify/views/FiledDelete.fxml"));
   Scene signUpScene = new Scene(signUpRoot);
   
   Stage signUpStage = new Stage();
   signUpStage.initStyle(StageStyle.TRANSPARENT);
   signUpStage.setScene(signUpScene);
   signUpStage.show();

   Stage splashSignInStage = (Stage) DeleteFieldBtn.getScene().getWindow();
   splashSignInStage.close();
}

   

@FXML
void ModifyField(ActionEvent event)throws IOException 
{
   Parent signUpRoot = FXMLLoader.load(getClass().getResource("/agrify/views/FiledModifier.fxml"));
   Scene signUpScene = new Scene(signUpRoot);
   
   Stage signUpStage = new Stage();
   signUpStage.initStyle(StageStyle.TRANSPARENT);
   signUpStage.setScene(signUpScene);
   signUpStage.show();

   Stage splashSignInStage = (Stage) ModifyFieldBtn.getScene().getWindow();
   splashSignInStage.close();
}
   



@FXML
void userFieldBack(ActionEvent event) throws IOException 

{
   Parent signUpRoot = FXMLLoader.load(getClass().getResource("/agrify/views/AdminDashboard.fxml"));
   Scene signUpScene = new Scene(signUpRoot);
   
   Stage signUpStage = new Stage();
   signUpStage.initStyle(StageStyle.TRANSPARENT);
   signUpStage.setScene(signUpScene);
   signUpStage.show();

   Stage splashSignInStage = (Stage) userFieldBackBtn.getScene().getWindow();
   splashSignInStage.close();
   
}
    
    
    
@FXML
void SearchField(ActionEvent event) throws SQLException 

{
  
    String fieldIdText = SearchFieldTextFieldBtn.getText();

    if (fieldIdText.isEmpty()) 
    {
        EditUserMessage11.setText("Please enter a field ID to search for!!");
    } 
    else 
    {
        try 
          {
                int fieldId = Integer.parseInt(fieldIdText);
                Field field = serviceField.getOne(fieldId);
                if (field != null) 
                    
                    {
                    fieldsList.clear();
                    fieldsList.add(field);
                    EditUserMessage11.setText("Field found.");
                    } 
                else    
                    {
                    EditUserMessage11.setText("No field found with ID " + fieldId);
                    }
          } 
        catch (NumberFormatException e) 
          {
            EditUserMessage11.setText("Invalid field ID. Please enter a valid numeric ID.");
          }
    }   
}


}



