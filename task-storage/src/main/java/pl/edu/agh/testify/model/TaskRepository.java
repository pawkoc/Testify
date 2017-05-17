package pl.edu.agh.testify.model;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

public interface TaskRepository {
    void save(MultipartFile file);
    Resource getFileAsResource(String filename) throws FileNotFoundException;
}
