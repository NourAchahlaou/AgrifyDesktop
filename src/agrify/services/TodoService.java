/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.services;

import agrify.entities.Todo;
import java.util.List;

/**
 *
 * @author ihebb
 */
public interface TodoService {
    void createTodo(Todo todo);

    Todo getTodoById(Long id);

    void updateTodo(Long id , Todo todo);

    void deleteTodo(Long id);
    
    List<Todo> getTodosByTaskId(Long taskId);

}
