package nl.timgoes.dbservice.modals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DbServiceMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbServiceMysqlApplication.class, args);
	}
}
