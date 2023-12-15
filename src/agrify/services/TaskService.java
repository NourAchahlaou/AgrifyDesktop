/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.services;

import agrify.entities.Task;
import agrify.payload.TaskFilter;
import java.util.List;

/**
 *
 * @author ihebb
 */
public interface TaskService {
    // Create a new task
    Task createTask(Task task);

    // Read a task by its ID
    Task getTaskById(Long id);

    // Update an existing task
    void updateTask(Long id , Task task);

    // Delete a task by its ID
    void deleteTask(Long id);

    // Get all tasks
    List<Task> getAllTasks();
    
    // Get filtered tasks based on filter criteria
    List<Task> getFilteredTasks(TaskFilter filter);
}