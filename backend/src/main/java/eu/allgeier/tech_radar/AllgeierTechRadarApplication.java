package eu.allgeier.tech_radar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.spring.data.firestore.repository.config.EnableReactiveFirestoreRepositories;

@SpringBootApplication
// @EnableAutoConfiguration
// @EnableReactiveFirestoreRepositories
public class AllgeierTechRadarApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllgeierTechRadarApplication.class, args);
	}

}
