package nl.timgoes.dbservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@ComponentScan(basePackages = "nl.timgoes")
@EntityScan(basePackages = "nl.timgoes")
@SpringBootApplication
public class DbServiceH2Application {

	public static void main(String[] args) {
		SpringApplication.run(DbServiceH2Application.class, args);
	}
}
