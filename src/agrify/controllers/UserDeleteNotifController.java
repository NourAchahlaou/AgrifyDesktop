package agrify.controllers;

import agrify.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UserDeleteNotifController {

    @FXML
    private Button DeleteNotifBackBtn;

    @FXML
    private Button DeleteNotifConfirmBtn;

    private boolean confirmation = false;

       private User selectedUser;

      public void setUserData(User user) {
        this.selectedUser = user;
    }

    public boolean isConfirmed() {
        return confirmation;
    }

    @FXML
    void DeleteNotifBack(ActionEvent event) {
        confirmation = false;
        Stage stage = (Stage) DeleteNotifBackBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void DeleteNotifConfirm(ActionEvent event) {
        confirmation = true;
        Stage stage = (Stage) DeleteNotifConfirmBtn.getScene().getWindow();
        stage.close();
    }
}
