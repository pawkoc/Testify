package pl.edu.agh.testify.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileSystemTaskRepositoryService implements TaskRepository {

    @Value("${task.storage.dir}")
    private String STORAGE_PATH;

    @Override
    public void save(MultipartFile file) throws IOException {
        Files.copy(file.getInputStream(), Paths.get(STORAGE_PATH).resolve(file.getOriginalFilename()),
                StandardCopyOption.REPLACE_EXISTING);
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
