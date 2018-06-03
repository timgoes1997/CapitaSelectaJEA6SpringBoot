package nl.timgoes.creditservice.creditservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class CreditServiceConfig {

    @LoadBalanced //Moet gebeuren voor euruka anders werkt de link herkennen niet
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
