package net.prueba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
@EnableConfigurationProperties
@SpringBootApplication
public class Prueba1Application {

	public static void main(String[] args) {
		SpringApplication.run(Prueba1Application.class, args);
	}

}
