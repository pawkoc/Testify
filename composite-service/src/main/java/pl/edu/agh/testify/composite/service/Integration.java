package pl.edu.agh.testify.composite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Integration {

    private final RestTemplate restTemplate;
    private final Util util;

    @Autowired
    public Integration(Util util) {
        this.util = util;
        this.restTemplate = new RestTemplate();
    }

    @Bean
    private RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
