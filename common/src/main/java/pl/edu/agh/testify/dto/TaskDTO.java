package pl.edu.agh.testify.dto;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class TaskDTO {

    private long id;
    private String taskName;
    private String description;

    public TaskDTO() {
    }

    public TaskDTO(long id, String taskName, String description) {
        this.id = id;
        this.taskName = taskName;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
