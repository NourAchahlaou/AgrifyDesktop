package agrify.controllers;

import agrify.entities.User;
import agrify.services.ServiceUser;
import agrify.utils.DataSource;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author tbagh
 */



public class UserHomeController {

    @FXML
    private Button AddUserBtn;

    @FXML
    private Button BestEmployeeBtn;

    @FXML
    private Button DeleteUserBtn;

    @FXML
    private TableView<User> EmployeeHome;

    @FXML
    private TableColumn<User, Integer> EmployeeHome_abscence;

    @FXML
    private TableColumn<User, String> EmployeeHome_email;

    @FXML
    private TableColumn<User, Integer> EmployeeHome_id;

    @FXML
    private TableColumn<User, String> EmployeeHome_nom;

    @FXML
    private TableColumn<User, String> EmployeeHome_phone;

    @FXML
    private TableColumn<User, String> EmployeeHome_prenom;

    @FXML
    private TableColumn<User, String> EmployeeHome_role;

    @FXML
    private Button ModifyUserBtn;

    @FXML
    private Button userHomeBackBtn;


    @FXML
    private Button PreseanceListeBtn;

    @FXML
    private Button SearchUserBtn;

    @FXML
    private TextField SearchUserTextFieldBtn;
    
    @FXML
    private Label EditUserMessage11;
    
    private ServiceUser userService;
    private ObservableList<User> usersList;

    
@FXML
  void initialize() 
    
  {
        userService = new ServiceUser(DataSource.getInstance().getConnection());
        usersList = FXCollections.observableArrayList();

        EmployeeHome_abscence.setCellValueFactory(new PropertyValueFactory<>("user_nbrabscence"));
        EmployeeHome_email.setCellValueFactory(new PropertyValueFactory<>("user_email"));
        EmployeeHome_id.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        EmployeeHome_nom.setCellValueFactory(new PropertyValueFactory<>("user_nom"));
        EmployeeHome_phone.setCellValueFactory(new PropertyValueFactory<>("user_telephone"));
        EmployeeHome_prenom.setCellValueFactory(new PropertyValueFactory<>("user_prenom"));
        EmployeeHome_role.setCellValueFactory(new PropertyValueFactory<>("user_role"));
        EmployeeHome.setItems(usersList);

        loadUserData();
  }

private void loadUserData() 

  {
        List<User> users = userService.getAll();
        usersList.addAll(users);
  }
    
      
    
@FXML
void AddUser(ActionEvent event) throws Exception

 {
        Parent signUpRoot = FXMLLoader.load(getClass().getResource("/agrify/views/AddUser.fxml"));
        Scene signUpScene = new Scene(signUpRoot);

        Stage signUpStage = new Stage();
        signUpStage.initStyle(StageStyle.TRANSPARENT);
        signUpStage.setScene(signUpScene);
        signUpStage.show();

        Stage splashSignInStage = (Stage) AddUserBtn.getScene().getWindow();
        splashSignInStage.close();
  }



@FXML
 void BestEmployee(ActionEvent event)throws Exception
 
 {
        Parent signUpRoot = FXMLLoader.load(getClass().getResource("/agrify/views/BestUser.fxml"));
        Scene signUpScene = new Scene(signUpRoot);

        Stage signUpStage = new Stage();
        signUpStage.initStyle(StageStyle.TRANSPARENT);
        signUpStage.setScene(signUpScene);
        signUpStage.show();

        Stage splashSignInStage = (Stage) BestEmployeeBtn.getScene().getWindow();
        splashSignInStage.close();
 }




@FXML
  void DeleteUser(ActionEvent event) throws Exception
  
    {
        Parent signUpRoot = FXMLLoader.load(getClass().getResource("/agrify/views/DeleteUser.fxml"));
        Scene signUpScene = new Scene(signUpRoot);

        Stage signUpStage = new Stage();
        signUpStage.initStyle(StageStyle.TRANSPARENT);
        signUpStage.setScene(signUpScene);
        signUpStage.show();

        Stage splashSignInStage = (Stage) DeleteUserBtn.getScene().getWindow();
        splashSignInStage.close();
    }



@FXML
  void ModifyUser(ActionEvent event) throws Exception
          
    {
        Parent signUpRoot = FXMLLoader.load(getClass().getResource("/agrify/views/ModifyUser.fxml"));
        Scene signUpScene = new Scene(signUpRoot);
 
        Stage signUpStage = new Stage();
        signUpStage.initStyle(StageStyle.TRANSPARENT);
        signUpStage.setScene(signUpScene);
        signUpStage.show();

        Stage splashSignInStage = (Stage) ModifyUserBtn.getScene().getWindow();
        splashSignInStage.close();
   }

    
@FXML
  void PreseanceListe(ActionEvent event)throws Exception
  
  {
        Parent signUpRoot = FXMLLoader.load(getClass().getResource("/agrify/views/PresenceUser.fxml"));
        Scene signUpScene = new Scene(signUpRoot);

        Stage signUpStage = new Stage();
        signUpStage.initStyle(StageStyle.TRANSPARENT);
        signUpStage.setScene(signUpScene);
        signUpStage.show();

        Stage splashSignInStage = (Stage) PreseanceListeBtn.getScene().getWindow();
        splashSignInStage.close();
   }

  
@FXML
void SearchUser(ActionEvent event) 
{
    String userIdText = SearchUserTextFieldBtn.getText();

    if (userIdText.isEmpty()) 
      {
            EditUserMessage11.setText("Please enter a user ID to search for.");
      } 
    else 
      {
        try 
         {
            int userId = Integer.parseInt(userIdText);
            User user = userService.getOne(userId);
            if (user != null) 
              {
                usersList.clear();
                usersList.add(user);
                EditUserMessage11.setText("User found.");
              } 
            else 
              {
                EditUserMessage11.setText("No user found with ID " + userId);
              }
         } 
        catch (NumberFormatException e) 
         {
            EditUserMessage11.setText("Invalid user ID. Please enter a valid numeric ID.");
         }
      }
}
    
    

   
@FXML
 void userHomeBack(ActionEvent event) throws Exception
 
    {
        Parent signUpRoot = FXMLLoader.load(getClass().getResource("/agrify/views/AdminDashboard.fxml"));
        Scene signUpScene = new Scene(signUpRoot);
   
        Stage signUpStage = new Stage();
        signUpStage.initStyle(StageStyle.TRANSPARENT);
        signUpStage.setScene(signUpScene);
        signUpStage.show();

        Stage splashSignInStage = (Stage) userHomeBackBtn.getScene().getWindow();
        splashSignInStage.close();
    }

}
