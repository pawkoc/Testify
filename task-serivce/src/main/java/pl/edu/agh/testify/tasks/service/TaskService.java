package pl.edu.agh.testify.tasks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.agh.testify.dto.TaskDTO;
import pl.edu.agh.testify.tasks.exceptions.TaskCannotBeSavedException;
import pl.edu.agh.testify.tasks.exceptions.TaskNotFoundException;
import pl.edu.agh.testify.tasks.model.Task;
import pl.edu.agh.testify.tasks.repository.TaskRepository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository repository;

    @Autowired
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }
    
    public Task saveTask(MultipartFile file, String taskName, String originalFileName, String summary) throws TaskCannotBeSavedException {
        try {
            byte[] bytes = file.getBytes();
            Task task = new Task(bytes, taskName, originalFileName, summary);
            return repository.save(task);
        } catch (IOException e) {
            throw new TaskCannotBeSavedException("Cannot save task with summary: " + summary, e);
        }
    }

    public Task getTask(long taskId) throws TaskNotFoundException {
        return repository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(String.format("Task with id [%d] does not exist", taskId)));
    }

    public List<TaskDTO> getAllTasksDTO() {
        List<Task> all = repository.findAll();
        return all.stream()
                .map(t -> new TaskDTO(t.getId(), t.getTaskName(), t.getSummary()))
                .collect(Collectors.toList());
    }
}
