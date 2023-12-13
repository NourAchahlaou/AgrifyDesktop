package agrify.controllers;

import agrify.entities.User;
import agrify.entities.Presence;
import agrify.services.ServiceUser;
import agrify.services.ServicePresence;
import agrify.utils.DataSource;
import java.time.LocalDate;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
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




public class UserPresenceController {

    @FXML
    private DatePicker PrescenceUserDateSearch;

    @FXML
    private TextField PrescenceUserIDSearch;

    @FXML
    private Button ActualiserPresenceUserBtn;

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
    private Button PresenceBackUserBtn1;

    @FXML
    private Button PresencePresenceBtn;

    @FXML
    private CheckBox PresenceUserAbscentBtn;

    //@FXML
  //  private CheckBox PresenceUserPrésentBtn;
    
    @FXML
    private Label PresenceMessage1;
        
    @FXML
    private Button PresenceSarchBtn;

    private ServiceUser userService;
    private ServicePresence presenceService;

    
    
public void initialize() 
            
        {
        userService = new ServiceUser(DataSource.getInstance().getConnection());
        presenceService = new ServicePresence(DataSource.getInstance().getConnection());
        initializeTableColumns();
        loadUserData();
        }


private void loadUserData() 
    
{
        List<User> users = userService.getAll();
        EmployeeHome.getItems().setAll(users);
    }
    
private void initializeTableColumns() 
    
    {
        EmployeeHome_abscence.setCellValueFactory(new PropertyValueFactory<>("user_nbrabscence"));
        EmployeeHome_email.setCellValueFactory(new PropertyValueFactory<>("user_email"));
        EmployeeHome_id.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        EmployeeHome_nom.setCellValueFactory(new PropertyValueFactory<>("user_nom"));
        EmployeeHome_phone.setCellValueFactory(new PropertyValueFactory<>("user_telephone"));
        EmployeeHome_prenom.setCellValueFactory(new PropertyValueFactory<>("user_prenom"));
        EmployeeHome_role.setCellValueFactory(new PropertyValueFactory<>("user_role"));
    }

    
@FXML
 void ActualiserPresenceUser(ActionEvent event) 
    {
        loadUserData();
    }
    
@FXML
 void PresenceSarch(ActionEvent event) 
 
 {
        String searchIdText = PrescenceUserIDSearch.getText();

        if (searchIdText.isEmpty()) 
          {
            PresenceMessage1.setText("Please enter a user ID to search for.");
          } 
        else 
          {
           try 
            {
                int searchId = Integer.parseInt(searchIdText);
                User user = userService.getOne(searchId);

                if (user == null) 
                  {
                    PresenceMessage1.setText("No user found with ID " + searchId);
                 } 
                else 
                 {
                    PresenceMessage1.setText("User found:");
                    EmployeeHome.getItems().clear();
                    EmployeeHome.getItems().add(user);
                 }
            } 
           catch (NumberFormatException e) 
            {
                PresenceMessage1.setText("Invalid user ID. Please enter a valid numeric ID.");
            }
         }
}
 
 @FXML
  void PresenceBackUser(ActionEvent event) throws Exception 
    
    {
        Parent signUpRoot = FXMLLoader.load(getClass().getResource("/agrify/views/UserHome.fxml"));
        Scene signUpScene = new Scene(signUpRoot);

        Stage signUpStage = new Stage();
        signUpStage.initStyle(StageStyle.TRANSPARENT);
        signUpStage.setScene(signUpScene);
        signUpStage.show();

        Stage currentStage = (Stage) PresenceBackUserBtn1.getScene().getWindow();
        currentStage.close();
    }
    
@FXML
 void PresencePresence(ActionEvent event) 
 
 {
    System.out.println("PresencePresence method started");

    int userId = Integer.parseInt(PrescenceUserIDSearch.getText());
    LocalDate selectedDate = PrescenceUserDateSearch.getValue();
    String presenceState = PresenceUserAbscentBtn.isSelected() ? "Absent" : "Present";

    Presence presence = new Presence(userId, selectedDate, presenceState);

    System.out.println("Presence object: " + presence);

    if (userService != null && presenceService != null) {
        System.out.println("userService and presenceService are not null");
        presenceService.savePresence(presence);

        User user = userService.getOne(userId);

        if (PresenceUserAbscentBtn.isSelected()) {
            int currentNbrAbsence = user.getUser_nbrabscence();
            currentNbrAbsence++;
            user.setUser_nbrabscence(currentNbrAbsence);
            userService.updateUserr(user);
        }
         else {
       
            System.out.println("PresenceUserAbscentBtn is not selected.");
        }
        
    } else {
        System.out.println("userService or presenceService is null");
    }

    loadUserData();
    System.out.println("PresencePresence method finished");
}

}
