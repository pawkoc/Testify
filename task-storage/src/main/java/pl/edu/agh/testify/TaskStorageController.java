package pl.edu.agh.testify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.testify.model.TaskRepository;

import java.io.FileNotFoundException;

@RestController
public class TaskStorageController {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskStorageController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
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
