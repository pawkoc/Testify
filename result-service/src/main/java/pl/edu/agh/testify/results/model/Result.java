package pl.edu.agh.testify.results.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Result {

    @Id @GeneratedValue
    private long id;

    private long studentId;
    private long taskId;
    private String grade;

    @OneToMany(cascade = CascadeType.ALL)
    private List<FailedTest> failedTests;

    public Result() {}

    public Result(long studentId, long taskId, String grade, List<FailedTest> failedTests) {
        this.studentId = studentId;
        this.taskId = taskId;
        this.grade = grade;
        this.failedTests = failedTests;
    }

    public long getId() {
        return id;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public List<FailedTest> getFailedTests() {
        return failedTests;
    }

    public void setFailedTests(List<FailedTest> failedTests) {
        this.failedTests = failedTests;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", taskId=" + taskId +
                ", failedTests=" + failedTests +
                '}';
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
