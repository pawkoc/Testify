package pl.edu.agh.testify.execution.service.executor;

import java.util.stream.Stream;

public interface TestProvider {
    Stream<Test> loadTests(long taskId);
}
