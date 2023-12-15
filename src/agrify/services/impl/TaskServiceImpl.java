package agrify.services.impl;

import agrify.entities.Task;
import agrify.entities.User;
import agrify.payload.TaskFilter;
import agrify.services.ServiceUser;
import agrify.services.TaskService;
import agrify.utils.DBCM;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class TaskServiceImpl implements TaskService {
    private DBCM dbcm;

    public TaskServiceImpl() {
        dbcm = DBCM.getInstance();
    }

    @Override
    public Task createTask(Task task) {
        Connection connection = dbcm.getConnection();

        try {
            String sql = "INSERT INTO task (task_title, assignedChef_id, creation_date, deadline, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, task.getTaskTitle());
            if (task.getAssignedChef() == null) {
                preparedStatement.setNull(2, Types.BIGINT);
            } else {
                preparedStatement.setLong(2, task.getAssignedChef().getUser_id());
            }
            preparedStatement.setDate(3, new Date(task.getCreationDate().getTime()));
            preparedStatement.setDate(4, new Date(task.getDeadline().getTime()));
            preparedStatement.setString(5, task.getStatus().name());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating task failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    task.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating task failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return task;
    }

    @Override
    public Task getTaskById(Long id) {
        Connection connection = dbcm.getConnection();
        Task task = null;
        try {
            String sql = "SELECT * FROM task WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                task = mapResultSetToTask(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    @Override
    public void updateTask(Long id, Task updatedTask) {
        Connection connection = dbcm.getConnection();

        try {
            String sql = "UPDATE task SET task_title = ?, assignedChef_id = ?, creation_date = ?, deadline = ?, status = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, updatedTask.getTaskTitle());
            if (updatedTask.getAssignedChef() != null) {
                preparedStatement.setLong(2, updatedTask.getAssignedChef().getUser_id());
            }else {
                preparedStatement.setLong(2,(Long) null);

            }
            preparedStatement.setDate(3, new java.sql.Date(updatedTask.getCreationDate().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(updatedTask.getDeadline().getTime()));
            preparedStatement.setString(5, updatedTask.getStatus().name());
            preparedStatement.setLong(6, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTask(Long id) {
        Connection connection = dbcm.getConnection();

        try {
            String sql = "DELETE FROM task WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Task> getAllTasks() {
        Connection connection = dbcm.getConnection();
        List<Task> taskList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM task";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Task task = mapResultSetToTask(resultSet);
                taskList.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskList;
    }
    
    @Override
    public List<Task> getFilteredTasks(TaskFilter filter) {
        Connection connection = dbcm.getConnection();
        List<Task> taskList = new ArrayList<>();

        try {
            // Start building the SQL query
            StringBuilder sql = new StringBuilder("SELECT * FROM task WHERE 1=1"); // Start with a true condition

            if (filter.getStatus() != null) {
                sql.append(" AND status = ?");
            }
            if (filter.getTaskTitle() != null) {
                sql.append(" AND task_title = ?");
            }
            if (filter.getDueBefore() != null) {
                sql.append(" AND deadline <= ?");
            }
            if (filter.getCreatedAfter() != null) {
                sql.append(" AND creation_date >= ?");
            }
            if (filter.getChefFilter() != null) {
                sql.append(" AND assignedChef_id = ?");
            }
            if (filter.getSearchTerm() != null) {
                sql.append(" AND task_title LIKE ?");
            }

            // Prepare the statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

            int parameterIndex = 1;

            if (filter.getStatus() != null) {
                preparedStatement.setString(parameterIndex++, filter.getStatus().name());
            }
            if (filter.getTaskTitle() != null) {
                preparedStatement.setString(parameterIndex++, filter.getTaskTitle());
            }
            if (filter.getDueBefore() != null) {
                preparedStatement.setDate(parameterIndex++, new java.sql.Date(filter.getDueBefore().getTime()));
            }
            if (filter.getCreatedAfter() != null) {
                preparedStatement.setDate(parameterIndex++, new java.sql.Date(filter.getCreatedAfter().getTime()));
            }
            if (filter.getChefFilter() != null) {
                preparedStatement.setLong(parameterIndex++, filter.getChefFilter().getUser_id());
            }
            if (filter.getSearchTerm() != null) {
                preparedStatement.setString(parameterIndex, "%" + filter.getSearchTerm() + "%");
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Task task = mapResultSetToTask(resultSet);
                taskList.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return taskList;
    }

    // Helper method to map ResultSet to Task
    private Task mapResultSetToTask(ResultSet resultSet) throws SQLException {
        Task task = new Task();
        ServiceUser serviceUser = new ServiceUser(dbcm.getConnection());
        User assignedChef = serviceUser.getOne((int) resultSet.getLong("assignedChef_id"));

        task.setId(resultSet.getLong("id"));
        task.setTaskTitle(resultSet.getString("task_title"));
        task.setAssignedChef(assignedChef);
        task.setCreationDate(resultSet.getDate("creation_date"));
        task.setDeadline(resultSet.getDate("deadline"));
        task.setStatus(Task.TaskStatus.valueOf(resultSet.getString("status")));
        return task;
    }
}