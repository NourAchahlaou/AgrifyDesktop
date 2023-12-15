/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.entities;

/**
 *
 * @author ihebb
 */
public class Todo {
    
    public enum TodoSeverity {
        Severe,
        Moderate,
        Low,
    }
    
    private Long id;
    private String todoDescription;
    private Task parentTask;
    private TodoSeverity severity;
    
    // No-arg constructor
    public Todo() {
    }

    // All-args constructor
    public Todo(String todoDescription, Task parentTask, TodoSeverity severity) {
        this.todoDescription = todoDescription;
        this.parentTask = parentTask;
        this.severity = severity;
    }
    
     @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", todoDescription='" + todoDescription + '\'' +
                ", parentTask=" + parentTask +
                ", severity=" + severity +
                '}';
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTodoDescription() {
        return todoDescription;
    }

    public void setTodoDescription(String todoDescription) {
        this.todoDescription = todoDescription;
    }

    public Task getParentTask() {
        return parentTask;
    }

    public void setParentTask(Task parentTask) {
        this.parentTask = parentTask;
    }

    public TodoSeverity getSeverity() {
        return severity;
    }

    public void setSeverity(TodoSeverity severity) {
        this.severity = severity;
    }
}
