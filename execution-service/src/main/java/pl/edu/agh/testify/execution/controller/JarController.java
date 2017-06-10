package pl.edu.agh.testify.execution.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/jar")
public class JarController {

    @PostMapping(path = "/{studentId}/{taskId}")
    public ResponseEntity<?> handleSolutionUpload(@PathVariable("studentId") long studentId,
                                                  @PathVariable("taskId") long taskId,
                                                  @RequestParam("file") MultipartFile jar) {
        return null;
    }
}
