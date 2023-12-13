package agrify.controllers;

import agrify.entities.User;
import agrify.services.ServiceUser;
import agrify.utils.DataSource;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import agrify.api.SendEmail;

/**
 *
 * @author tbagh
 */

public class UserAddController {

   @FXML
    private Button AddUser0Btn;
   
    @FXML
    private Label AddUserEmailMessage;

    @FXML
    private Label AddUserMessage;

    @FXML
    private Label AddUserPhoneMessage;

    @FXML
    private Label AddUserUsernameMessage;

    @FXML
    private Button AddUserBackBtn1;

    @FXML
    private TextField UserEmailField;

    @FXML
    private TextField UserFNameField;

    @FXML
    private TextField UserLNameField;

    @FXML
    private TextField UserPasswordField;

    @FXML
    private TextField UserPhoneField;

    @FXML
    private ComboBox<String> UserRoleField;

    @FXML
    private ComboBox<String> UserSexField;

    @FXML
    private TextField UserUsernameField;
    
    
     private ServiceUser userService; // You need to set this when initializing the controller

    public void setUserService(ServiceUser userService) {
        this.userService = userService;
    }
    
    
    public void initialize() {

  ObservableList<String> sexList = FXCollections.observableArrayList(
                "Homme", "Femme");
                UserSexField.setItems(sexList);
                UserSexField.setValue("Select --------"); 

    ObservableList<String> RoleList = FXCollections.observableArrayList(
                "User", "Admin");
                UserRoleField.setItems(RoleList);
                UserRoleField.setValue("Select --------"); 
}
    

    
    

  @FXML
void Add0User(ActionEvent event) throws IOException 

{
    ServiceUser userService = new ServiceUser(DataSource.getInstance().getConnection());
    
      String emaill = UserEmailField.getText();
    if (!emaill.contains("@")) {
        AddUserEmailMessage.setText("Invalid email format (missing '@').");
        return; 
    } else {
        AddUserEmailMessage.setText("");
    }
    
    String telephonee = UserPhoneField.getText();
        if (userService.isPhoneExists(telephonee)) {
            AddUserPhoneMessage.setText("Phone number already exists. Choose another.");
            return; 
        } else {
            AddUserPhoneMessage.setText(""); 
        }

     String usernamee = UserUsernameField.getText();
        if (userService.isUsernameExists(usernamee)) {
            AddUserUsernameMessage.setText("Username already exists. Choose another.");
                return; 
        } else {
            AddUserUsernameMessage.setText(""); 
                }
        
        
    System.out.println("Database instance connection");
    System.out.println(DataSource.getInstance().getConnection());
    
    String nom = UserFNameField.getText();
    String prenom = UserLNameField.getText();
    String email = UserEmailField.getText();
    String telephone = UserPhoneField.getText();
    String role = UserRoleField.getValue(); 
    String genre = UserSexField.getValue(); 
    int nbrAbsence = 0; 
    String username = UserUsernameField.getText();
    String password = UserPasswordField.getText();
    
    
    User user = new User(nom, prenom, email, telephone, role, genre, nbrAbsence, username, password);
    userService.ajouter(user);
    SendEmail emailSender = new SendEmail();
emailSender.sendEmail(email, "Welcome to Our Application", "Hello, " + nom + "! You have been successfully registered.\n\n"
        + "Votre Username est: " + username + " et votre Password est: " + password);

        Parent signUpRoot = FXMLLoader.load(getClass().getResource("/agrify/views/UserHome.fxml"));
        Scene signUpScene = new Scene(signUpRoot);
   
        Stage signUpStage = new Stage();
        signUpStage.initStyle(StageStyle.TRANSPARENT);
        signUpStage.setScene(signUpScene);
        signUpStage.show();

        Stage splashSignInStage = (Stage) AddUser0Btn.getScene().getWindow();
    splashSignInStage.close();
}


    @FXML
    void AddUserBack(ActionEvent event) throws Exception
    {

        Parent signUpRoot = FXMLLoader.load(getClass().getResource("/agrify/views/UserHome.fxml"));
        Scene signUpScene = new Scene(signUpRoot);
   
        Stage signUpStage = new Stage();
        signUpStage.initStyle(StageStyle.TRANSPARENT);
        signUpStage.setScene(signUpScene);
        signUpStage.show();

        Stage splashSignInStage = (Stage) AddUserBackBtn1.getScene().getWindow();
        splashSignInStage.close();
    }

    
    @FXML
    void Role(ActionEvent event) 
      {
        String selectedRole = UserRoleField.getSelectionModel().getSelectedItem();
        System.out.println("Selected Role: " + selectedRole);
      }

    @FXML
    void Sex(ActionEvent event) 
      {
          
        String selectedSex = UserSexField.getSelectionModel().getSelectedItem();
        System.out.println("Selected Sex: " + selectedSex);
      
      }
    
}
