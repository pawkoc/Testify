package pl.edu.agh.testify.client.model;

import pl.edu.agh.testify.dto.TaskDTO;

public class Task {

    private long id;
    private String taskName;
    private String description;
    private String url;

    public Task() {}

    public Task(TaskDTO taskDTO) {
        this(taskDTO.getId(), taskDTO.getTaskName(), taskDTO.getDescription(), LinkBuilder.task(taskDTO.getId()));
    }

    public Task(long id, String taskName, String description, String url) {
        this.id = id;
        this.taskName = taskName;
        this.description = description;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
