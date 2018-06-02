package nl.timgoes.userservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class UserServiceConfig {

    @LoadBalanced //Moet gebeuren voor euruka anders werkt het niet
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
