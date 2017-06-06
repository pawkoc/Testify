package pl.edu.agh.testify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TaskSummaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskSummaryApplication.class, args);
    }
}
