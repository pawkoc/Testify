package pl.edu.agh.testify.rabbitmq.message;

import java.util.Map;

public class TestResultMessage {

    private final long studentId;
    private final long taskId;
    private final String grade;
    private final Map<String, String> failedTests;

    public TestResultMessage(long studentId, long taskId, String grade, Map<String, String> failedTests) {
        this.studentId = studentId;
        this.taskId = taskId;
        this.grade = grade;
        this.failedTests = failedTests;
    }

    public long getStudentId() {
        return studentId;
    }

    public long getTaskId() {
        return taskId;
    }

    public String getGrade() {
        return grade;
    }

    public Map<String, String> getFailedTests() {
        return failedTests;
    }
}
