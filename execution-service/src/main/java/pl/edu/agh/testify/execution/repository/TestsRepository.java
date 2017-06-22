package pl.edu.agh.testify.execution.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.testify.execution.model.Tests;

@Repository
public interface TestsRepository extends MongoRepository<Tests, String> {
    Tests findByTaskId(long taskId);
}
