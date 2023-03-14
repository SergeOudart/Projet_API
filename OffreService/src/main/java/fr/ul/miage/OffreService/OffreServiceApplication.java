package fr.ul.miage.OffreService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class OffreServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OffreServiceApplication.class, args);
	}

	@Bean
	RestTemplate template() {
		return new RestTemplate();
	}

}
