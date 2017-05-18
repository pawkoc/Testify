package pl.edu.agh.testify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.testify.model.Summary;
import pl.edu.agh.testify.model.SummaryRepository;

@RestController
public class TaskSummaryController {

    private final SummaryRepository repository;

    @Autowired
    public TaskSummaryController(SummaryRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Summary> createSummary(@RequestBody Summary summary) {
        Summary s = repository.save(new Summary(summary.getTaskName(),
                summary.getProfessorName(),
                summary.getSummary()));
        return new ResponseEntity<>(s, HttpStatus.OK);
    }
}
