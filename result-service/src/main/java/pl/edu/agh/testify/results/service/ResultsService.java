package pl.edu.agh.testify.results.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.testify.dto.ResultDTO;
import pl.edu.agh.testify.rabbitmq.message.TestResultMessage;
import pl.edu.agh.testify.results.model.FailedTest;
import pl.edu.agh.testify.results.model.Result;
import pl.edu.agh.testify.results.repository.ResultsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultsService {

    private final ResultsRepository resultsRepository;

    @Autowired
    public ResultsService(ResultsRepository resultsRepository) {
        this.resultsRepository = resultsRepository;
    }

    public void saveResults(TestResultMessage message) {
        Result result = resultsRepository.findByStudentIdAndTaskId(message.getStudentId(), message.getTaskId());
        if (result == null) {
            result = convertMessage(message);
        } else {
            result.setGrade(message.getGrade());
            result.setFailedTests(convertFailedTests(message));
        }
        resultsRepository.save(result);
    }

    private Result convertMessage(TestResultMessage message) {
        List<FailedTest> failedTests = convertFailedTests(message);
        return new Result(message.getStudentId(), message.getTaskId(), message.getGrade(), failedTests);
    }

    private List<FailedTest> convertFailedTests(TestResultMessage message) {
        return message.getFailedTests().entrySet().stream()
                    .map(e -> new FailedTest(e.getKey(), e.getValue()))
                    .collect(Collectors.toList());
    }

    public ResultDTO getResultByStudentIdAndTaskId(long studentId, long taskId) {
        Result result = resultsRepository.findByStudentIdAndTaskId(studentId, taskId);
        if (result == null) {
            return new ResultDTO();
        }
        return new ResultDTO(taskId, result.getGrade(), failedTestsToString(result.getFailedTests()));
    }

    private String failedTestsToString(List<FailedTest> failedTests) {
        return failedTests.stream()
                .map(t -> "[" + t.getExpectedOutput() + ":" + t.getActualOutput() + "]")
                .collect(Collectors.joining(","));
    }
}
