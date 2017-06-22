package pl.edu.agh.testify.results.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.testify.results.model.Result;

@Repository
public interface ResultsRepository extends JpaRepository<Result, Long> {
    Result findByStudentIdAndTaskId(long studentId, long taskId);
}
