package pl.edu.agh.testify.execution.service.queue;

import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class DefaultTaskQueue implements TaskQueue {

    private final BlockingQueue<TaskData> queue = new LinkedBlockingQueue<>();

    @Override
    public boolean addTask(TaskData taskData) {
        return queue.add(taskData);
    }

    @Override
    public TaskData getTask() throws InterruptedException {
        return queue.take();
    }
}
