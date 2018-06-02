package nl.timgoes.dbservice.dbservicemysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DbServiceModelApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbServiceModelApplication.class, args);
	}
}