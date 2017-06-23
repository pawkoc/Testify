package pl.edu.agh.testify.client.model;

public class NewTask {

    private String taskName;
    private String description;
    private String path;

    public NewTask() {
    }

    public NewTask(String taskName, String description, String path) {
        this.taskName = taskName;
        this.description = description;
        this.path = path;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "NewTask{" +
                "taskName='" + taskName + '\'' +
                ", description='" + description + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
