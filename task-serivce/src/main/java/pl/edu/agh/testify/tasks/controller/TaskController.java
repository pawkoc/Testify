package pl.edu.agh.testify.tasks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.edu.agh.testify.tasks.exceptions.TaskCannotBeSavedException;
import pl.edu.agh.testify.tasks.exceptions.TaskNotFoundException;
import pl.edu.agh.testify.tasks.model.Task;
import pl.edu.agh.testify.tasks.service.TaskService;

import java.net.URI;

@RestController
@RequestMapping(path = "/task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(path = "/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") String file,
                                        @RequestParam("taskName") String taskName,
                                        @RequestParam("summary") String summary) throws TaskCannotBeSavedException {
        MultipartFile preparedFile = new MockMultipartFile(taskName, taskName,
                                "text/plain", file.getBytes());
        Task task = taskService.saveTask(preparedFile, taskName, preparedFile.getOriginalFilename(), summary);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/{taskId}")
                .buildAndExpand(task.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/{taskId}")
    public ResponseEntity<Resource> getTaskSources(@PathVariable("taskId") long taskId) throws TaskNotFoundException {
        Task task = taskService.getTask(taskId);
        byte[] fileBytes = task.getTaskData();
        Resource fileResource = new ByteArrayResource(fileBytes);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+task.getOriginalFileName()+"\"")
                .body(fileResource);
    }
}
