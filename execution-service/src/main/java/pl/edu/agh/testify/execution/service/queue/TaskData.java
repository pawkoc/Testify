package pl.edu.agh.testify.execution.service.queue;

import lombok.EqualsAndHashCode;
import pl.edu.agh.testify.execution.service.executor.Test;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode
public class TaskData {

    private final long taskId;
    private final long studentId;
    private final List<Test> tests;
    private final Path jar;

    public TaskData(long taskId, long studentId, List<Test> tests, Path jar) {
        this.taskId = taskId;
        this.studentId = studentId;
        this.tests = Collections.unmodifiableList(tests);
        this.jar = jar;
    }

    public long getTaskId() {
        return taskId;
    }

    public long getStudentId() {
        return studentId;
    }

    public List<Test> getTests() {
        return tests;
    }

    public Path getJar() {
        return jar;
    }
}
