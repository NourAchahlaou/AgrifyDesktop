package agrify.DTO;

import agrify.entities.Task;
import agrify.entities.User;
import java.util.Date;

public class TaskTableDTO {
    private Long id;
    private String taskTitle;
    private Date creationDate;
    private Date deadline;
    private String status;
    private String assignedChef;

    public TaskTableDTO(Long id , String taskTitle, Date creationDate, Date deadline, String status, String assignedChef) {
        this.id = id;
        this.taskTitle = taskTitle;
        this.creationDate = creationDate;
        this.deadline = deadline;
        this.status = status;
        this.assignedChef = assignedChef;
    }
    
    public TaskTableDTO(){
    }
    
    public Long getId(){
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
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

    public String getAssignedChef() {
        return assignedChef;
    }

    public void setAssignedChef(String assignedChef) {
        this.assignedChef = assignedChef;
    }
    
    
    
    public static TaskTableDTO mapToTaskTableDTO(Task task) {
        if (task == null) {
            return null;
        }
        
        TaskTableDTO taskTableDTO = new TaskTableDTO();
        
        taskTableDTO.setId(task.getId());
        taskTableDTO.setTaskTitle(task.getTaskTitle());
        taskTableDTO.setCreationDate(task.getCreationDate());
        taskTableDTO.setDeadline(task.getDeadline());
        taskTableDTO.setStatus(task.getStatus().name());
        
        User assignedChef = task.getAssignedChef();
        if (assignedChef != null) {
            taskTableDTO.setAssignedChef(assignedChef.getUsername());
        } else {
            taskTableDTO.setAssignedChef(null);
        }

        return taskTableDTO;
    }
}