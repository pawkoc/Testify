package pl.edu.agh.testify.execution;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import pl.edu.agh.testify.execution.service.executor.TaskExecutor;

@SpringBootApplication
//@EnableDiscoveryClient
public class ExecutionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExecutionApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(TaskExecutor executor) {
        return (args) -> executor.start();
    }
}
