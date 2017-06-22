package pl.edu.agh.testify.execution.repository;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public interface TemporaryJarStorageRepository {
    Optional<Path> save(long studentId, long taskId, MultipartFile file) throws IOException;
    Path getSolutionPath(long studentId, long taskId) throws FileNotFoundException;
}
