package pl.edu.agh.testify.execution.service.queue;

public interface TaskQueue {
    boolean addTask(TaskData taskData);
    TaskData getTask() throws InterruptedException;
}
