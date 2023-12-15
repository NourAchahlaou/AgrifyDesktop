package agrify.entities;

import java.util.Date;

public class Task {
    public enum TaskStatus {
        Open,
        In_progress,
        Completed,
        In_review,
        Canceled
    }

    private Long id;
    private String taskTitle;
    private User assignedChef; 
    private Date creationDate;
    private Date deadline;
    private TaskStatus status;

    public Task() {
    }

    public Task(Long id, String taskTitle, User assignedChef, Date creationDate, Date deadline, TaskStatus status) {
        this.id = id;
        this.taskTitle = taskTitle;
        this.assignedChef = assignedChef;
        this.creationDate = creationDate;
        this.deadline = deadline;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskTitle='" + taskTitle + '\'' +
                ", assignedChef=" + assignedChef +
                ", creationDate=" + creationDate +
                ", deadline=" + deadline +
                ", status=" + status +
                '}';
    }

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

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
    
}





