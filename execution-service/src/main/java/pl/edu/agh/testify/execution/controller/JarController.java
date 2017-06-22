package pl.edu.agh.testify.execution.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.agh.testify.execution.service.RunTestService;

@RestController
@RequestMapping(path = "/jar")
public class JarController {

    private final RunTestService runTestService;

    @Autowired
    public JarController(RunTestService runTestService) {
        this.runTestService = runTestService;
    }

    @PostMapping(path = "/{studentId}/{taskId}")
    public boolean handleSolutionUpload(@PathVariable("studentId") long studentId,
                                                  @PathVariable("taskId") long taskId,
                                                  @RequestParam("file") MultipartFile jar) {
        return runTestService.uploadSolution(studentId, taskId, jar);
    }
}
