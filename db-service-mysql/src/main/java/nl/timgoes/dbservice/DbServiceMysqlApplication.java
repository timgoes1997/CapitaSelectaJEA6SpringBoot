package nl.timgoes.dbservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@ComponentScan("nl.timgoes.core")
@SpringBootApplication
public class DbServiceMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbServiceMysqlApplication.class, args);
	}
}
