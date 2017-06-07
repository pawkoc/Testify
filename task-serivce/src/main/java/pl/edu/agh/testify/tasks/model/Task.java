package pl.edu.agh.testify.tasks.model;

import javax.persistence.*;

@Entity
public class Task {

    @Id @GeneratedValue
    private long id;

    @Lob
    private byte[] taskData;

    private String taskName;
    private String originalFileName;
    private String summary;

    public Task() {
    }

    public Task(byte[] taskData, String taskName, String originalFileName, String summary) {
        this.taskData = taskData;
        this.taskName = taskName;
        this.originalFileName = originalFileName;
        this.summary = summary;
    }

    public long getId() {
        return id;
    }

    public byte[] getTaskData() {
        return taskData;
    }

    public void setTaskData(byte[] taskData) {
        this.taskData = taskData;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
