package pl.edu.agh.testify.execution.service.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.testify.execution.service.queue.TaskData;
import pl.edu.agh.testify.execution.service.queue.TaskQueue;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class DefaultTaskExecutor implements TaskExecutor {

    private static Logger logger = LoggerFactory.getLogger(DefaultTaskExecutor.class);

    private final int THREADS = 4;
    private final TaskQueue queue;
    private final ExecutorService executor = Executors.newFixedThreadPool(THREADS);

    @Autowired
    public DefaultTaskExecutor(TaskQueue queue) {
        this.queue = queue;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void start() throws InterruptedException {
        logger.info("Thread executor started");
        while (true) {
            logger.info("Waiting for task to come...");
            TaskData task = queue.getTask();
            logger.info("Got task!");
            CompletableFuture<TestsResult> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return TaskProcessor.executeJar(task.getJar(), task.getTests());
                } catch (IOException e) {
                    logger.error("Error with processing Jar", e);
                }
                return null;
            }, executor);
            logger.info("Task left for execution");
            future.thenAccept(testsResult -> logger.info("TEST RESULT READY " + testsResult + " GRADE: " + testsResult.grade()));
        }
    }

    @Override
    public void stop() {
        executor.shutdown();
    }
}
