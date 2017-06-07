package pl.edu.agh.testify.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import pl.edu.agh.testify.tasks.model.Task;
import pl.edu.agh.testify.tasks.repository.TaskRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class TaskApplication {

    private static final Logger log = LoggerFactory.getLogger(TaskApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(TaskRepository repository) {
        return (args) -> {
            // save a couple of customers
            repository.save(new Task("1", "1", "1"));
            repository.save(new Task("2", "2", "2"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Task customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Task customer = repository.findOne(1L);
            log.info("Customer found with findOne(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");
        };
    }
}
