package pl.edu.agh.testify.execution.service.executor;

public interface TaskExecutor {
    void start() throws InterruptedException;
    void stop();
}
