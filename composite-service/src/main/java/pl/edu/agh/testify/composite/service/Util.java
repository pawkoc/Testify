package pl.edu.agh.testify.composite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Optional;

@Component
public class Util {

    private final LoadBalancerClient loadBalancer;

    @Autowired
    public Util(LoadBalancerClient loadBalancer) {
        this.loadBalancer = loadBalancer;
    }

    public Optional<URI> getServiceUrl(String serviceId) {
        try {
            ServiceInstance instance = loadBalancer.choose(serviceId);
            return Optional.of(instance.getUri());
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }
}
