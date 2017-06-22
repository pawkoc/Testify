package pl.edu.agh.testify.execution.service.executor;

import pl.edu.agh.testify.rabbitmq.message.TestResultMessage;

import java.util.Map;
import java.util.stream.Collectors;

class TestResultAdapter {

    static TestResultMessage convert(long studentId, long taskId, TestsResult result) {
        String gradeStr = result.grade().toString();
        Map<String, String> testsMap = result.getFailed().stream()
                .collect(Collectors.toMap(Test::expectedOutput, Test::output));
        return new TestResultMessage(studentId, taskId, gradeStr, testsMap);
    }
}
