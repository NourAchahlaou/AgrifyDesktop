/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.services.impl;

import agrify.entities.Task;
import agrify.entities.Todo;
import agrify.services.TodoService;
import agrify.utils.DBCM;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ihebb
 */
public class TodoServiceImpl implements TodoService{
    private DBCM dbcm;

    public TodoServiceImpl() {
        dbcm = DBCM.getInstance();
    }
    
    @Override
    public void createTodo(Todo todo) {
        Connection connection = dbcm.getConnection();

        try {
            String sql = "INSERT INTO todo (todo_description, parent_task_id, severity) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, todo.getTodoDescription());
            preparedStatement.setLong(2, todo.getParentTask().getId());
            preparedStatement.setString(3, todo.getSeverity().name());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
     @Override
    public Todo getTodoById(Long id) {
        Connection connection = dbcm.getConnection();
        Todo todo = null;

        try {
            String sql = "SELECT * FROM todo WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                todo = mapResultSetToTodo(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todo;
    }
    
     @Override
    public void updateTodo(Long id, Todo updatedTodo) {
        Connection connection = dbcm.getConnection();

        try {
            String sql = "UPDATE todo SET todo_description = ?, parent_task_id = ?, severity = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, updatedTodo.getTodoDescription());
            preparedStatement.setLong(2, updatedTodo.getParentTask().getId());
            preparedStatement.setString(3, updatedTodo.getSeverity().name());
            preparedStatement.setLong(4, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTodo(Long id) {
        Connection connection = dbcm.getConnection();

        try {
            String sql = "DELETE FROM todo WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Todo> getTodosByTaskId(Long taskId) {
        List<Todo> todos = new ArrayList<>();
        Connection connection = dbcm.getConnection();
        
        try {
           String sql = "SELECT * FROM todo WHERE parent_task_id= ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql); 
            preparedStatement.setLong(1, taskId);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Todo todo = mapResultSetToTodo(resultSet);
                todos.add(todo);
            }
        } catch (SQLException e){
            
        }

        return todos;
    }
    
     private Todo mapResultSetToTodo(ResultSet resultSet) throws SQLException {
        Todo todo = new Todo();
        Task parentTask = new TaskServiceImpl().getTaskById(resultSet.getLong("parent_task_id"));
        todo.setId(resultSet.getLong("id"));
        todo.setTodoDescription(resultSet.getString("todo_description"));
        todo.setParentTask(parentTask);
        todo.setSeverity(Todo.TodoSeverity.valueOf(resultSet.getString("severity")));
        return todo;
    }
    
    
}
