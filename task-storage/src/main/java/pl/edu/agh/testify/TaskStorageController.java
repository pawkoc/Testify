package pl.edu.agh.testify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.edu.agh.testify.model.TaskRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

@RestController
public class TaskStorageController {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskStorageController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            taskRepository.save(file);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/{filename}")
                    .buildAndExpand(file.getOriginalFilename()).toUri();
            return ResponseEntity.created(location).build();
        } catch (IOException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @RequestMapping(value = "/files/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        try {
            Resource file = taskRepository.getFileAsResource(filename);
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                    .body(file);
        } catch (FileNotFoundException e) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }
}
