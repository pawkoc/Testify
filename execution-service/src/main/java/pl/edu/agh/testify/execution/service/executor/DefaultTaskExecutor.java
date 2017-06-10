package pl.edu.agh.testify.execution.service.executor;

import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
public class DefaultTaskExecutor implements TaskExecutor {

    private final Executor executor = Executors.newFixedThreadPool(4);

    public void executeJar() {

    }
}
