/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.DTO;

import agrify.entities.Task;
import agrify.entities.Todo;
import agrify.entities.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ihebb
 */
public class TaskDetailsDTO {
     private Long id;
    private String taskTitle;
    private User assignedChef; 
    private Date creationDate;
    private Date deadline;
    private String status;
    private List<Todo> todos;
    
     public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public User getAssignedChef() {
        return assignedChef;
    }

    public void setAssignedChef(User assignedChef) {
        this.assignedChef = assignedChef;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }
    
     public static TaskDetailsDTO mapToTaskDetails(Task task, List<Todo> todos) {
        if (task == null) {
            return null;
        }
        
         System.out.println(task);
        
        TaskDetailsDTO taskDetailsDTO = new TaskDetailsDTO();
        taskDetailsDTO.setId(task.getId());
        taskDetailsDTO.setTaskTitle(task.getTaskTitle());
        taskDetailsDTO.setAssignedChef(task.getAssignedChef());
        taskDetailsDTO.setCreationDate(task.getCreationDate());
        taskDetailsDTO.setDeadline(task.getDeadline());
        if(task.getStatus() != null){
        taskDetailsDTO.setStatus(task.getStatus().name());
        }
        taskDetailsDTO.setTodos(todos);

        return taskDetailsDTO;
    }
     
     public String toString() {
        return "TaskDetailsDTO{" +
                "id=" + id +
                ", taskTitle='" + taskTitle + '\'' +
                ", assignedChef=" + assignedChef +
                ", creationDate=" + creationDate +
                ", deadline=" + deadline +
                ", status='" + status + '\'' +
                ", todos=" + todos +
                '}';
    }
    
    public TaskDetailsDTO() {
        this.todos = new ArrayList<>();
    }
     
    public Task mapToTask() {
    Task task = new Task();
    task.setId(this.id);
    task.setTaskTitle(this.taskTitle);
    task.setAssignedChef(this.assignedChef);
    task.setCreationDate(this.creationDate);
    task.setDeadline(this.deadline);
    task.setStatus(Task.TaskStatus.valueOf(this.status));

    return task;
}
}
