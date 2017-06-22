package pl.edu.agh.testify.execution.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "tests")
public class Tests {

    @Id
    public String id;

    private long taskId;
    private Map<String, String> tests;

    public Tests(long taskId, Map<String, String> tests) {
        this.taskId = taskId;
        this.tests = tests;
    }

    public long getTaskId() {
        return taskId;
    }

    public Map<String, String> getTests() {
        return tests;
    }
}
