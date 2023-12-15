/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.payload;

import agrify.entities.Task;
import java.util.Date;
import agrify.entities.User;

public class TaskFilter {
    private Task.TaskStatus status;
    private String taskTitle;
    private Date dueBefore;
    private Date createdAfter;
    private User chefFilter;
    private String searchTerm;

    // No-args constructor
    public TaskFilter() {
    }

    // All-args constructor
    public TaskFilter(Task.TaskStatus status, String taskTitle, Date dueBefore, Date createdAfter, User chefFilter, String searchTerm) {
        this.status = status;
        this.taskTitle = taskTitle;
        this.dueBefore = dueBefore;
        this.createdAfter = createdAfter;
        this.chefFilter = chefFilter;
        this.searchTerm = searchTerm;
    }

    
    @Override
    public String toString() {
        return "TaskFilter{" +
                "status=" + status +
                ", taskTitle='" + taskTitle + '\'' +
                ", dueBefore=" + dueBefore +
                ", createdAfter=" + createdAfter +
                ", chefFilter=" + chefFilter +
                ", searchTerm='" + searchTerm + '\'' +
                '}';
    }
    
    // Getters and setters
    public Task.TaskStatus getStatus() {
        return status;
    }

    public void setStatus(Task.TaskStatus status) {
        this.status = status;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public Date getDueBefore() {
        return dueBefore;
    }

    public void setDueBefore(Date dueBefore) {
        this.dueBefore = dueBefore;
    }

    public Date getCreatedAfter() {
        return createdAfter;
    }

    public void setCreatedAfter(Date createdAfter) {
        this.createdAfter = createdAfter;
    }

    public User getChefFilter() {
        return chefFilter;
    }

    public void setChefFilter(User chefFilter) {
        this.chefFilter = chefFilter;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }
}