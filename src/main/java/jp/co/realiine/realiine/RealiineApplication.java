package jp.co.realiine.realiine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource("beans-realiine-service.xml")
@SpringBootApplication
public class RealiineApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealiineApplication.class, args);
	}

}

