package pl.edu.agh.testify.execution.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.agh.testify.execution.repository.TemporaryJarStorageRepository;
import pl.edu.agh.testify.execution.repository.TestsRepository;
import pl.edu.agh.testify.execution.service.executor.StringTest;
import pl.edu.agh.testify.execution.service.executor.Test;
import pl.edu.agh.testify.execution.service.queue.TaskData;
import pl.edu.agh.testify.execution.service.queue.TaskQueue;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RunTestService {

    private final TemporaryJarStorageRepository jarRepository;
    private final TestsRepository testsRepository;
    private final TaskQueue taskQueue;

    @Autowired
    public RunTestService(TemporaryJarStorageRepository jarRepository, TestsRepository testsRepository, TaskQueue taskQueue) {
        this.jarRepository = jarRepository;
        this.testsRepository = testsRepository;
        this.taskQueue = taskQueue;
    }

    public boolean uploadSolution(long studentId, long taskId, MultipartFile jar) {
        try {
            Optional<Path> path = jarRepository.save(studentId, taskId, jar);
            if (!path.isPresent()) return false;
            path.ifPresent(p -> {
                List<Test> tests = prepareTests(taskId);
                TaskData data = new TaskData(taskId, studentId, tests, p);
                taskQueue.addTask(data);
            });
        } catch (IOException e) {
            return false;
        }
        return false;
    }

    private List<Test> prepareTests(long taskId) {
        Map<String, String> testMap = testsRepository.findByTaskId(taskId).getTests();
        return testMap.entrySet().stream()
                .map(e -> new StringTest(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }
}
