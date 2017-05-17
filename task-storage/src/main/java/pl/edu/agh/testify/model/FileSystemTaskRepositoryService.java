package pl.edu.agh.testify.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileSystemTaskRepositoryService implements TaskRepository {

    @Value("${task.storage.dir}")
    private String STORAGE_PATH;

    @Override
    public void save(MultipartFile file) {

    }

    @Override
    public Resource getFileAsResource(String filename) throws FileNotFoundException {
        Path file = load(filename);
        try {
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileNotFoundException(filename);
            }
        } catch (MalformedURLException e) {
            throw new FileNotFoundException(filename);
        }
    }

    private Path load(String filename) {
        return Paths.get(STORAGE_PATH).resolve(filename);
    }
}
