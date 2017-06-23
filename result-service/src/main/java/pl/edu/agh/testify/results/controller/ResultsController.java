package pl.edu.agh.testify.results.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.testify.dto.ResultDTO;
import pl.edu.agh.testify.results.model.Result;
import pl.edu.agh.testify.results.service.ResultsService;

@RestController
public class ResultsController {

    private final ResultsService resultsService;

    @Autowired
    public ResultsController(ResultsService resultsService) {
        this.resultsService = resultsService;
    }

    @GetMapping(value = "/result/{studentId}/{taskId}")
    public ResultDTO getByStudentIdAndTaskId(@PathVariable long studentId, @PathVariable long taskId) {
        return resultsService.getResultByStudentIdAndTaskId(studentId, taskId);
    }
}
