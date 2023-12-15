/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.controllers;

import agrify.entities.Todo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ihebb
 */
public class TodoItemController implements Initializable {
    @FXML
    private ComboBox<Todo.TodoSeverity> priorityCombobox;

    @FXML
    private TextField todoTextInput;
    
    @FXML
    private Button deleteTodoBtn;
    
    
     ObservableList<Todo.TodoSeverity> priorityList;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        priorityList = FXCollections.observableArrayList(Todo.TodoSeverity.values());
        priorityCombobox.setItems(priorityList);
    }
    
     // Getter for ComboBox
    public ComboBox<Todo.TodoSeverity> getPriorityCombobox() {
        return priorityCombobox;
    }

    // Setter for ComboBox
    public void setPriorityCombobox(ComboBox<Todo.TodoSeverity> priorityCombobox) {
        this.priorityCombobox = priorityCombobox;
    }

    // Getter for TextField
    public TextField getTodoTextInput() {
        return todoTextInput;
    }

    // Setter for TextField
    public void setTodoTextInput(TextField todoTextInput) {
        this.todoTextInput = todoTextInput;
    }

    // Getter for Button
    public Button getDeleteTodoBtn() {
        return deleteTodoBtn;
    }

    // Setter for Button
    public void setDeleteTodoBtn(Button deleteTodoBtn) {
        this.deleteTodoBtn = deleteTodoBtn;
    }
    
}
