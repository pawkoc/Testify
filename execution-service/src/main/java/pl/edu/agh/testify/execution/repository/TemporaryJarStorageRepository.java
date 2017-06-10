package pl.edu.agh.testify.execution.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

@Repository
public interface TemporaryJarStorageRepository {
    void save(MultipartFile file) throws IOException;
    Path getSolutionPath(long studentId, long taskId) throws FileNotFoundException;
}
