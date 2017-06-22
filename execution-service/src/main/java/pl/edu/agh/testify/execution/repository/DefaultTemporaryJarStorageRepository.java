package pl.edu.agh.testify.execution.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Repository
public class DefaultTemporaryJarStorageRepository implements TemporaryJarStorageRepository {

    private static final Logger logger = LoggerFactory.getLogger(DefaultTemporaryJarStorageRepository.class);

    // TODO: 2017-06-22 properties
    private final Path rootLocation = Paths.get("D:", "TESTIFY", "solutions");

    @Override
    public Optional<Path> save(long studentId, long taskId, MultipartFile file) {
        Path parentDirs = rootLocation.resolve(studentId+"").resolve(taskId+"");
        Path finalPath = parentDirs.resolve(file.getOriginalFilename());
        try {
            Files.createDirectories(parentDirs);
            Files.copy(file.getInputStream(), finalPath);
        } catch (IOException e) {
            logger.error("Cannot save solution file " + file.getOriginalFilename(), e);
            Optional.empty();
        }
        return Optional.of(finalPath);
    }

    @Override
    public Path getSolutionPath(long studentId, long taskId) throws FileNotFoundException {
        return null;
    }
}
