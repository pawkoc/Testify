package pl.edu.agh.testify.model;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface TaskRepository {
    void save(MultipartFile file) throws IOException;
    Resource getFileAsResource(String filename) throws FileNotFoundException;
}
