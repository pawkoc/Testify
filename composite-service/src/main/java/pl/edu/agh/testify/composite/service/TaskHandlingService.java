package pl.edu.agh.testify.composite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TaskHandlingService {

    private final Integration integration;

    @Autowired
    public TaskHandlingService(Integration integration) {
        this.integration = integration;
    }

    public void addTask(String taskName, String summary, MultipartFile taskFile) {

    }
}
