package pl.edu.agh.testify.client.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import pl.edu.agh.testify.client.model.NewTask;
import pl.edu.agh.testify.client.model.Task;
import pl.edu.agh.testify.dto.ResultDTO;
import pl.edu.agh.testify.dto.TaskDTO;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    private final RestTemplate restTemplate;

    @Autowired
    public TaskService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void save(NewTask task) {
        logger.info("SERVICE SAVE TASK : " + task);
        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        Path file = Paths.get(task.getPath());
        try {
            map.add("file", new UrlResource(file.toUri()));
            map.add("taskName", task.getTaskName());
            map.add("summary", task.getDescription());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
            String url = "http://localhost:11226/task/upload";
            ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
            logger.info("RESPONSE: " + response.toString());
        } catch (MalformedURLException e) {
            logger.error("ERROR WITH FILE PATH", e);
        }
    }

    public List<Task> allTasks() {
        ResponseEntity<TaskDTO[]> entity = restTemplate.getForEntity("http://localhost:11226/task/all", TaskDTO[].class);
        TaskDTO[] dtos = entity.getBody();
        List<TaskDTO> dtoList = Arrays.asList(dtos);
        String resUrl = "http://localhost:11228/result/1/";
        List<ResultDTO> resultDTOS = dtoList.stream()
                .map(dto -> restTemplate.getForEntity(resUrl + dto.getId(), ResultDTO.class))
                .map(HttpEntity::getBody)
                .collect(Collectors.toList());


        List<Task> result = new ArrayList<>();
        for (TaskDTO taskDto : dtos) {
            ResultDTO resultDTO = resultDTOS.stream()
                    .filter(r -> r.getTaskId() == taskDto.getId())
                    .findFirst().orElse(new ResultDTO());
            result.add(new Task(taskDto, resultDTO));
        }

        return result;
    }
}
