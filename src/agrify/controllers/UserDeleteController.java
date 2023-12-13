package agrify.controllers;

import agrify.entities.User;
import agrify.services.ServiceUser;
import agrify.utils.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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



public class UserDeleteController {

    @FXML
    private Button DeleteBackUserBtn1;

    @FXML
    private Button DeleteSearchUserBtn;

    @FXML
    private Button DeleteDeleteUserBtn;

    @FXML
    private Label EditUserMessage11;

    @FXML
    private TableView<User> EmployeeHome;

    @FXML
    private TableColumn<User, Integer> EmployeeHome_id;

    @FXML
    private TableColumn<User, String> EmployeeHome_nom;

    @FXML
    private TableColumn<User, String> EmployeeHome_prenom;

    @FXML
    private TableColumn<User, String> EmployeeHome_role;
    
        @FXML
    private TableColumn<User, Integer> EmployeeHome_abscence;

    @FXML
    private TextField SearchModifyUserTextFieldBtn;
    private Connection connect;
    private ServiceUser userService;
    private ObservableList<User> usersList;
    private boolean isSearching = false;

    @FXML
    void initialize() 
    {

        connect = DataSource.getInstance().getConnection();
        userService = new ServiceUser(connect);
        usersList = FXCollections.observableArrayList();

        EmployeeHome_id.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        EmployeeHome_nom.setCellValueFactory(new PropertyValueFactory<>("user_nom"));
        EmployeeHome_prenom.setCellValueFactory(new PropertyValueFactory<>("user_prenom"));
        EmployeeHome_role.setCellValueFactory(new PropertyValueFactory<>("user_role"));   
        EmployeeHome_abscence.setCellValueFactory(new PropertyValueFactory<>("user_nbrabscence"));

        EmployeeHome.setItems(usersList);
        loadUserTable();
    }

    @FXML
    void DeleteBackUser(ActionEvent event) throws Exception
       
      {
        
        Parent signUpRoot = FXMLLoader.load(getClass().getResource("/agrify/views/UserHome.fxml"));
        Scene signUpScene = new Scene(signUpRoot);
   
        Stage signUpStage = new Stage();
        signUpStage.initStyle(StageStyle.TRANSPARENT);
        signUpStage.setScene(signUpScene);
        signUpStage.show();

        Stage splashSignInStage = (Stage) DeleteBackUserBtn1.getScene().getWindow();
        splashSignInStage.close();
    
      }

    @FXML
    void DeleteSearchUser(ActionEvent event)
        {
            String searchName = SearchModifyUserTextFieldBtn.getText();
                if (!searchName.isEmpty())  
                  {
                    try 
                    {
                        usersList.clear();
                        usersList.addAll(userService.searchUsersByName(searchName));
                        isSearching = true;
                    } 
                    catch (SQLException ex) 
                        {
                            EditUserMessage11.setText("An error occurred while searching for users.");
                            ex.printStackTrace();
                        }
                  } 
                else 
                  {
                    EditUserMessage11.setText("Please enter a name to search for.");
                  }
        }

    
    
  @FXML
void DeleteDeleteUser(ActionEvent event) {
    if (isSearching) {
        User selectedUser = EmployeeHome.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/agrify/views/UserSupprimerNotif.fxml"));
                Parent notifRoot = loader.load();
                UserDeleteNotifController notifController = loader.getController();
                notifController.setUserData(selectedUser);

                Stage notifStage = new Stage();
                notifStage.initStyle(StageStyle.TRANSPARENT);
                notifStage.setScene(new Scene(notifRoot));
                notifStage.showAndWait();

                boolean confirmation = notifController.isConfirmed();

                if (confirmation) {
                    userService.supprimerByName(selectedUser.getUser_nom());
                    usersList.remove(selectedUser);
                    EditUserMessage11.setText("Utilisateur supprimé avec succès.");
                } else {
                    EditUserMessage11.setText("Suppression annulée.");
                }
            } catch (IOException e) {
            }
        } else {
            EditUserMessage11.setText("Sélectionnez un utilisateur à supprimer.");
        }
    } else {
        EditUserMessage11.setText("Recherchez des utilisateurs d'abord, s'il vous plaît.");
    }
}

    


    private void loadUserTable()
        
     {
        List<User> users = userService.getAll();
        usersList.addAll(users);
     }
        
}
