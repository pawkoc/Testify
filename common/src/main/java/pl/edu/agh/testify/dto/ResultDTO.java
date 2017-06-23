package pl.edu.agh.testify.dto;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class ResultDTO {

    private long taskId;
    private String grade;
    private String failedTests;

    public ResultDTO() {
    }

    public ResultDTO(long taskId, String grade, String failedTests) {
        this.taskId = taskId;
        this.grade = grade;
        this.failedTests = failedTests;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getFailedTests() {
        return failedTests;
    }

    public void setFailedTests(String failedTests) {
        this.failedTests = failedTests;
    }
}
