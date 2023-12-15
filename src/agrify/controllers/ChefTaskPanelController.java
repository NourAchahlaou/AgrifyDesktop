/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.controllers;

import agrify.DTO.TaskDetailsDTO;
import agrify.DTO.TaskTableDTO;
import agrify.entities.Task;
import agrify.entities.Todo;
import agrify.entities.User;
import agrify.payload.TaskFilter;
import agrify.services.ServiceUser;
import agrify.services.TaskService;
import agrify.services.TodoService;
import agrify.services.impl.SendEmail;
import agrify.services.impl.TaskServiceImpl;
import agrify.services.impl.TodoServiceImpl;
import agrify.utils.DateUtils;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ihebb
 */
public class ChefTaskPanelController {

    @FXML
    private Button back_btn;
    
    @FXML
    private Button addTaskBtn;
    
    @FXML
    private Button editPopupExitBtn;

    @FXML
    private Button all_tasks_switch;
    
    @FXML
    private Button addTodoBtn;
    
    @FXML
    private Button saveBtn;
    
    @FXML
    private Button popupMessageBtn;


    @FXML
    private Label popupMessageText;
     
    @FXML
    private TableColumn<TaskTableDTO, Integer> assignedChefField;


    @FXML
    private ComboBox<User> chefDroplistFilter;


    @FXML
    private TableColumn<TaskTableDTO, Date> creationDateField;

    @FXML
    private TextField currentDateInput;

    @FXML
    private TableColumn<TaskTableDTO, Date> deadlineField;

    @FXML
    private TextField nbrTodosInput;

    @FXML
    private TextField nom_ingredient_popup_ingredient_management;

    @FXML
    private TextField nom_ingredient_popup_ingredient_management2;

    @FXML
    private TextField nom_ingredient_popup_ingredient_management21;

    @FXML
    private AnchorPane popup_add_task;

    @FXML
    private AnchorPane popup_edit_task;
    
    @FXML
    private AnchorPane popup_message;

    @FXML
    private TextField prix_popup_ingredient_management;

    @FXML
    private TextField prix_popup_ingredient_management1;

    @FXML
    private ImageView quitAddPopupBtn;

    @FXML
    private ImageView quitEditPopupBtn;

    @FXML
    private Button resetFiltersBtn;

    @FXML
    private TextField searchInput;

    @FXML
    private TableColumn<TaskTableDTO, String> statusField;

    @FXML
    private ComboBox<User> taskAssignedChefInput;

    @FXML
    private DatePicker taskDeadlineDateInput;

    @FXML
    private TextField taskNameInput;

    @FXML
    private ComboBox<Task.TaskStatus> taskStatusInput;

    @FXML
    private TableColumn<TaskTableDTO, String> taskTitleField;

    @FXML
    private Button task_done_switch;

    @FXML
    private Button task_in_progress_switch;

    @FXML
    private Button task_in_review_switch;

    @FXML
    private AnchorPane tasksPage;

    @FXML
    private TableView<TaskTableDTO> tasksTable;

    @FXML
    private AnchorPane todoInputGroup;

    @FXML
    private AnchorPane todoInputGroup1;

    @FXML
    private ComboBox<?> type_ingredient_popup_ingredient_management;

    @FXML
    private ComboBox<?> type_ingredient_popup_ingredient_management2;

    @FXML
    private DatePicker dueBeforeDatePicker;
    
    @FXML
    private DatePicker createdAfterDatePicker;
    
     @FXML
    private VBox todoContainer;
    
    @FXML
    void AddIngredient(ActionEvent event) {

    }

    @FXML
    void exit(ActionEvent event) throws Exception{
        Parent adminTaskPanel = FXMLLoader.load(getClass().getResource("/agrify/views/AdminDashboard.fxml"));
        Scene animalDashboardScene = new Scene(adminTaskPanel);

        Stage animalDashboardStage = new Stage();
        animalDashboardStage.initStyle(StageStyle.TRANSPARENT);
        animalDashboardStage.setScene(animalDashboardScene);
        animalDashboardStage.show();

        Stage signInStage = (Stage) back_btn.getScene().getWindow();
        signInStage.close();
    }

    @FXML
    void filterByAllTasks(MouseEvent event) {

    }

    @FXML
    void filterByDoneTasks(MouseEvent event) {

    }

    @FXML
    void filterByInProgressTasks(MouseEvent event) {

    }

    @FXML
    void filterByInReviewTasks(MouseEvent event) {

    }

    @FXML
    void quitEditPopup(MouseEvent event) {

    }

    TaskService taskService;
    TodoService todoService;
    ServiceUser userService;
    // State variables
    private TaskFilter currentTaskFilters = new TaskFilter();
    private TaskDetailsDTO editedTask = new TaskDetailsDTO();
    private TaskDetailsDTO addedTask = new TaskDetailsDTO();
    ObservableList<User> usersObservableList;
    ObservableList<Task.TaskStatus> statusList;
    // To whether add or edit a task when the popup comes
    Boolean isEditingTask = false;
    Long editedTaskId;
    public ChefTaskPanelController() {
        taskService = new TaskServiceImpl();
        todoService = new TodoServiceImpl();
        userService = new ServiceUser();
    }
    
    // This class is for extracting the username and displaying it in the combobox
    private class UserListCell extends ListCell<User> {
        @Override
        protected void updateItem(User user, boolean empty) {
            super.updateItem(user, empty);
            if (empty || user == null) {
                setText(null);
            } else {
                setText(user.getUsername()); // Display the username
            }
        }
    }

    @FXML
    void initialize() {
        popup_add_task.setVisible(false);
        popup_edit_task.setVisible(false);

        initTableColumns();
        initTableEvents();
        initChefDroplistFilter();

        updateFiltersDisplay();
        
        
    }
    
    void initChefDroplistFilter(){
        // Retrieve the list of all users
        List<User> allUsers = userService.getAll();

        // Convert the list to an ObservableList
        usersObservableList = FXCollections.observableArrayList(allUsers);

        // Set the ComboBox items to the list of users
        chefDroplistFilter.setItems(usersObservableList);
        taskAssignedChefInput.setItems(usersObservableList);
        // Customize how the usernames are displayed
        chefDroplistFilter.setCellFactory(new Callback<ListView<User>, ListCell<User>>() {
            @Override
            public ListCell<User> call(ListView<User> param) {
                return new UserListCell();
            }
        });
        
        chefDroplistFilter.setButtonCell(new ListCell<User>() {
            @Override
            protected void updateItem(User item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText("");
                } else {
                    setText(item.getUsername());
                }
            }
        });
        
        taskAssignedChefInput.setCellFactory(new Callback<ListView<User>, ListCell<User>>() {
            @Override
            public ListCell<User> call(ListView<User> param) {
                return new UserListCell();
            }
        });
        
        taskAssignedChefInput.setButtonCell(new ListCell<User>() {
            @Override
            protected void updateItem(User item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText("");
                } else {
                    setText(item.getUsername());
                }
            }
        });
    }
    
    void initTableEvents(){
        // Double click event for when to select 
        tasksTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { 
                TaskTableDTO selectedItem = tasksTable.getSelectionModel().getSelectedItem();
                 
                Long selectedItemId = selectedItem.getId();
                try {
                    showTaskEditorWithId(selectedItemId);
                } catch (IOException ex) {
                    Logger.getLogger(AdminTaskPanelController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    
    
    void initTableColumns(){
        // Initialize the column mappings
        taskTitleField.setCellValueFactory(new PropertyValueFactory<>("taskTitle"));
        creationDateField.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        deadlineField.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        statusField.setCellValueFactory(new PropertyValueFactory<>("status"));
        
    }

    void updateTaskListFilterTabSwitches() {
        // Remove the activeFilterBtn style class from all buttons
        all_tasks_switch.getStyleClass().remove("activeFilterBtn");
        task_in_progress_switch.getStyleClass().remove("activeFilterBtn");
        task_in_review_switch.getStyleClass().remove("activeFilterBtn");
        task_done_switch.getStyleClass().remove("activeFilterBtn");

        if (currentTaskFilters.getStatus() == null) {
            all_tasks_switch.getStyleClass().add("activeFilterBtn");
        } else {
            switch (currentTaskFilters.getStatus()) {
                case In_progress:
                    task_in_progress_switch.getStyleClass().add("activeFilterBtn");
                    break;
                case In_review:
                    task_in_review_switch.getStyleClass().add("activeFilterBtn");
                    break;
                case Completed:
                    task_done_switch.getStyleClass().add("activeFilterBtn");
                    break;
                // Handle other cases as needed
            }
        }

    }

    void populateTable(List<Task> tasks) {
        // Create an ObservableList to hold your data
        ObservableList<TaskTableDTO> taskList = FXCollections.observableArrayList(new ArrayList<TaskTableDTO>());

        // Map Task objects to TaskTableDTO and add to taskList
        for (Task task : tasks) {
            TaskTableDTO taskDTO = TaskTableDTO.mapToTaskTableDTO(task);
            taskList.add(taskDTO);
        }

        // Set the TableView's items to the ObservableList
        tasksTable.setItems(taskList);
    }

    void updateTableState() {
        // Fetch all tasks from the TaskService
        List<Task> tasks = taskService.getFilteredTasks(currentTaskFilters);

        populateTable(tasks);

        updateTaskListFilterTabSwitches();
    }

    void showTaskEditorWithId(Long id) throws IOException{
        popup_edit_task.setVisible(true);
        this.isEditingTask = true;
        this.editedTask = null;
        TaskDetailsDTO taskDetails = getTaskDetails(id);
        this.editedTask = taskDetails;
        System.out.println(taskDetails);
        this.editedTaskId = id;
        
        mapTaskDetailsToView();
        editTaskUpdateCheck();
        setUpTaskStatus();
    }
    
    void showAddTask() throws IOException{
        popup_edit_task.setVisible(true);
        this.isEditingTask = false;
        Task newTask = new Task();
        List<Todo> newTodoList= new ArrayList<Todo>();
        this.editedTask = TaskDetailsDTO.mapToTaskDetails(newTask, newTodoList);
        this.editedTaskId = null;
        System.out.println(this.editedTask);
        resetTaskDetailsView();
        editTaskUpdateCheck();
        setUpTaskStatus();
    }
    
    void setUpTaskStatus(){
        statusList = FXCollections.observableArrayList(Task.TaskStatus.values());
        taskStatusInput.setItems(statusList);
        
    }
    
    TaskDetailsDTO getTaskDetails(Long id){
        Task task = taskService.getTaskById(id);
        List<Todo> todoList= todoService.getTodosByTaskId(id);
        System.out.println(todoList);
        return TaskDetailsDTO.mapToTaskDetails(task, todoList);
    }
    
    void mapTaskDetailsToView() throws IOException{
        System.out.println(editedTask);
        // Edited Task must be setup before mapping the object to view
        taskNameInput.setText(editedTask.getTaskTitle());
        taskStatusInput.setValue(Task.TaskStatus.valueOf(editedTask.getStatus()));
        currentDateInput.setText(DateUtils.getDateString(editedTask.getCreationDate(), "yyyy-MM-dd") );
        taskDeadlineDateInput.setValue(DateUtils.convertToLocalDate(editedTask.getDeadline()));
        nbrTodosInput.setText(String.valueOf(editedTask.getTodos().size()));
        User userToSelect = null;
        for (User user : usersObservableList) {
            if (user.getUser_id()== editedTask.getAssignedChef().getUser_id()) {
                userToSelect = user;
                break; // Found the user, exit the loop
            }
        }
        taskAssignedChefInput.setValue(userToSelect);
        
        mapTodosToView();
    }
    
    void resetTaskDetailsView()throws IOException{
        taskNameInput.setText("");
        taskStatusInput.setValue(null);
        currentDateInput.setText("");
        taskDeadlineDateInput.setValue(null);
        nbrTodosInput.setText("0");
        taskAssignedChefInput.setValue(null);
    }
    
    void mapTodosToView() throws IOException{
        todoContainer.getChildren().clear();
        for (Todo todo : editedTask.getTodos()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/agrify/views/TodoItem.fxml"));
            AnchorPane todoAnchorPane = loader.load();
            
            todoContainer.getChildren().add(todoAnchorPane);
            TodoItemController todoController = loader.getController();
            
            TextField todoTextField = todoController.getTodoTextInput();
            ComboBox<Todo.TodoSeverity> priorityComboBox = todoController.getPriorityCombobox();
            Button deleteTodoBtn = todoController.getDeleteTodoBtn();
            
            // Set the values in the TextField and ComboBox
            todoTextField.setText(todo.getTodoDescription());
            priorityComboBox.setValue(todo.getSeverity());

            // Attach event listeners to track changes
            todoTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                // Update the corresponding Todo object or set a flag to indicate changes
                todo.setTodoDescription(newValue);
                System.out.println(todo);
            });

            priorityComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
                // Update the corresponding Todo object or set a flag to indicate changes
                todo.setSeverity(newValue);
                System.out.println(todo);


            });
            
            deleteTodoBtn.setOnAction(event -> {
                // Remove the associated todo from your data model
                editedTask.getTodos().remove(todo);

                // Remove the todo item from the view
                todoContainer.getChildren().remove(todoAnchorPane);
                nbrTodosInput.setText(String.valueOf(editedTask.getTodos().size()));
            });
        }
    }
    
    void mapTodosToView_added() throws IOException{
        todoContainer.getChildren().clear();
        for (Todo todo : addedTask.getTodos()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/agrify/views/TodoItem.fxml"));
            AnchorPane todoAnchorPane = loader.load();
            
            todoContainer.getChildren().add(todoAnchorPane);
            TodoItemController todoController = loader.getController();
            
            TextField todoTextField = todoController.getTodoTextInput();
            ComboBox<Todo.TodoSeverity> priorityComboBox = todoController.getPriorityCombobox();
            Button deleteTodoBtn = todoController.getDeleteTodoBtn();
            
            // Set the values in the TextField and ComboBox
            todoTextField.setText(todo.getTodoDescription());
            priorityComboBox.setValue(todo.getSeverity());

            // Attach event listeners to track changes
            todoTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                // Update the corresponding Todo object or set a flag to indicate changes
                todo.setTodoDescription(newValue);
                System.out.println(todo);
            });

            priorityComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
                // Update the corresponding Todo object or set a flag to indicate changes
                todo.setSeverity(newValue);
                System.out.println(todo);


            });
            
            deleteTodoBtn.setOnAction(event -> {
                // Remove the associated todo from your data model
                addedTask.getTodos().remove(todo);

                // Remove the todo item from the view
                todoContainer.getChildren().remove(todoAnchorPane);
                nbrTodosInput.setText(String.valueOf(addedTask.getTodos().size()));
            });
        }
    }
    
    void editTaskUpdateCheck(){
       taskNameInput.textProperty().addListener((observable, oldValue, newValue) -> {
            // Update the corresponding Todo object or set a flag to indicate changes
            editedTask.setTaskTitle(newValue);
        });
       // add a value change listener for 
       taskDeadlineDateInput.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Update the corresponding Todo object or set a flag to indicate changes
            editedTask.setDeadline(DateUtils.convertDateToLocalDate(newValue) );
        });
       
       taskAssignedChefInput.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Update the corresponding Todo object or set a flag to indicate changes
            editedTask.setAssignedChef(newValue);
        });
       
       taskStatusInput.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Update the corresponding Todo object or set a flag to indicate changes
            editedTask.setStatus(newValue.name());
        });
    }
    
    
    @FXML
    void addTodoToTask(ActionEvent event) throws Exception{
        if(this.isEditingTask){
            Todo newTodo = new Todo();
            newTodo.setParentTask(editedTask.mapToTask());
            editedTask.getTodos().add(newTodo);
            mapTodosToView();
            nbrTodosInput.setText(String.valueOf(editedTask.getTodos().size()));
        }else {
            Todo newTodo = new Todo();
            addedTask.getTodos().add(newTodo);
            mapTodosToView_added();
            nbrTodosInput.setText(String.valueOf(addedTask.getTodos().size()));
        }
    }
    
    @FXML
    void onPopupMessageClick(ActionEvent event) throws Exception{
        popup_message.setVisible(false);
    }
    
    @FXML
    void onAddTaskClick(ActionEvent event) throws Exception{
        this.isEditingTask = false;
        showAddTask();
        this.addedTask = new TaskDetailsDTO();
        Date currentDate = new Date();
        this.addedTask.setCreationDate(currentDate);
        currentDateInput.setText(DateUtils.getDateString(currentDate, "yyyy-MM-dd") );
    }
    
    
    
    @FXML
    void onSaveClicked(ActionEvent event) throws Exception{
        if(this.isEditingTask){
            //Validating task
            if(!validateTaskInputs()) return;
            // Validating todos
            for(Todo todo : editedTask.getTodos()){
                if(!validateTodoInput(todo))return;
            }
            // Persist todos
            List<Todo> originalTodoList= todoService.getTodosByTaskId(this.editedTaskId);

            for (Todo originalTodo : originalTodoList) {
                boolean found = false;
                for (Todo editedTodo : editedTask.getTodos()) {
                    if (editedTodo.getId() != null && editedTodo.getId().equals(originalTodo.getId())) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    // The originalTodo does not exist in editedTask's todos; delete it
                    todoService.deleteTodo(originalTodo.getId());
                }
            }
            
            for(Todo todo : editedTask.getTodos()){
                if(todo.getId() != null){
                    todoService.updateTodo(todo.getId(), todo);
                }else {
                    System.out.println("creating todo");
                    System.out.println(todo);
                    todoService.createTodo(todo);
                }
            }
            
            
            Task originalTaskData = taskService.getTaskById(editedTaskId);
            
            if(originalTaskData.getAssignedChef().getUser_id() != editedTask.getAssignedChef().getUser_id()){
                // Assigned chef changed
                // Send mail to the first user telling him the task is canceled 
                SendEmail.sendEmail(editedTask.getAssignedChef().getUser_email(), "TASK ABORTED: "+ editedTask.getTaskTitle(), "The task previously assigned"+editedTask.getTaskTitle()+" has been aborted!");

                // Send mail to the new user to inform him with the new task 
                SendEmail.sendEmail(editedTask.getAssignedChef().getUser_email(), "NEW TASK ASSIGNED: "+ editedTask.getTaskTitle(), editedTask.toString());
                
            }
           
            
            // Persisting the task changes
            System.out.println(editedTask.mapToTask());
            taskService.updateTask(editedTaskId, editedTask.mapToTask());
            popup_edit_task.setVisible(false);
            updateTableState();
            
        }
        else {
            // Filling addedTask object with form data          
           
            addedTask.setTaskTitle(taskNameInput.textProperty().getValue());
            addedTask.setDeadline(DateUtils.convertDateToLocalDate(taskDeadlineDateInput.getValue()) );
            addedTask.setAssignedChef(taskAssignedChefInput.getValue());
            addedTask.setStatus(taskStatusInput.getValue().name());
            
            System.out.println(addedTask);

            //Validating task
            if(!validateTaskInputs_added()) return;
            // Validating todos
            for(Todo todo : addedTask.getTodos()){
                if(!validateTodoInput(todo))return;
            }
            
            Task createdTask = taskService.createTask(addedTask.mapToTask());
            
            for(Todo todo : addedTask.getTodos()){
               
                System.out.println("creating todo");
                System.out.println(todo);
                todo.setParentTask(createdTask);
                todoService.createTodo(todo);
                
            }

            popup_edit_task.setVisible(false);
            updateTableState();
        }
    }
    
    boolean validateTodoInput(Todo todo){
        
        if(todo.getSeverity() == null){
            popup_message.setVisible(true);
            popupMessageText.setText("Priorité Todo n'est pas selectioné");
            return false;
        }
        
        if(todo.getTodoDescription() == null || todo.getTodoDescription().length() < 3){
            popup_message.setVisible(true);
            popupMessageText.setText("La description du Todo ne peux pas etre vide ou moin de 3 characteres");
            return false;
        }
        return true;
    }
    
    boolean validateTaskInputs(){
        if (editedTask == null) {
            // Handle the case where editedTask is null, this might indicate an issue.
            return false;
        }
        if (editedTask.getTaskTitle() == null || editedTask.getTaskTitle().length() < 3) {
            popup_message.setVisible(true);
            popupMessageText.setText("Titre du tache ne doit pas etre vide ou moin de 3 characteres");
            return false;
        }
        if (editedTask.getDeadline().before(editedTask.getCreationDate())) {
            popup_message.setVisible(true);
            popupMessageText.setText("Date d'expiration est deja expiré");
            return false;
        }
        if (editedTask.getAssignedChef() == null) {
            popup_message.setVisible(true);
            popupMessageText.setText("Pas de chef attribué au tache");
            return false;
        }
        return true;
    }
    
    
    boolean validateTaskInputs_added(){
        if (addedTask == null) {
            // Handle the case where editedTask is null, this might indicate an issue.
            return false;
        }
        if (addedTask.getTaskTitle() == null || addedTask.getTaskTitle().length() < 3) {
            popup_message.setVisible(true);
            popupMessageText.setText("Titre du tache ne doit pas etre vide ou moin de 3 characteres");
            return false;
        }
        if (addedTask.getDeadline().before(addedTask.getCreationDate())) {
            popup_message.setVisible(true);
            popupMessageText.setText("Date d'expiration est deja expiré");
            return false;
        }
        if (addedTask.getAssignedChef() == null) {
            popup_message.setVisible(true);
            popupMessageText.setText("Pas de chef attribué au tache");
            return false;
        }
        return true;
    }
    
    

    @FXML
    void switchAllTasks(ActionEvent event) throws Exception {

        currentTaskFilters.setStatus(null);
        updateFiltersDisplay();
    }

    @FXML
    void switchInprogress(ActionEvent event) throws Exception {
        currentTaskFilters.setStatus(Task.TaskStatus.In_progress);
        updateFiltersDisplay();
    }

    @FXML
    void switchInReview(ActionEvent event) throws Exception {
        currentTaskFilters.setStatus(Task.TaskStatus.In_review);
        updateFiltersDisplay();
    }

    @FXML
    void switchDone(ActionEvent event) throws Exception {
        currentTaskFilters.setStatus(Task.TaskStatus.Completed);
        updateFiltersDisplay();
    }

    @FXML
    void updateSearchTerm() {
        currentTaskFilters.setSearchTerm(searchInput.getText());
        updateFiltersDisplay();
    }

    @FXML
    void resetTaskFilters() {
        System.out.println("resetting");
        currentTaskFilters = new TaskFilter();
        updateFiltersDisplay();
    }
    
    @FXML
    void onChefFilterChange(){
        System.out.println("changed chef");
        currentTaskFilters.setChefFilter(chefDroplistFilter.getValue());
        updateFiltersDisplay();
    }
    
    @FXML
    void onDueBeforeDateChange(){
        currentTaskFilters.setDueBefore(DateUtils.convertDateToLocalDate(dueBeforeDatePicker.getValue()));
        updateFiltersDisplay();
    }
    
     @FXML
    void onCreatedAfterDateChange(){
        currentTaskFilters.setCreatedAfter(DateUtils.convertDateToLocalDate(createdAfterDatePicker.getValue()));
        //dueBeforeDatePicker.valueProperty().set(createdAfterDatePicker.getValue());
        updateFiltersDisplay();

    }
    
    @FXML
    void onEditPopupExit(ActionEvent event) {
        popup_edit_task.setVisible(false);
        todoContainer.getChildren().clear();
    }
    
    

    void updateFiltersDisplay() {
        searchInput.setText(currentTaskFilters.getSearchTerm());
        updateTaskListFilterTabSwitches();
        chefDroplistFilter.setValue(currentTaskFilters.getChefFilter());
        //createdAfterDatePicker.setValue(currentTaskFilters.getCreatedAfter());
        //Update data
        updateTableState();
    }
    
}
