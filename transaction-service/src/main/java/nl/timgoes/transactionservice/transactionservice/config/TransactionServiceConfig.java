package nl.timgoes.transactionservice.transactionservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TransactionServiceConfig {

    @LoadBalanced //Moet gebeuren voor euruka anders werkt het niet
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
