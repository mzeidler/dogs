package dogs.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
		"dogs.controller",
		"dogs.service",
		"dogs.app"
})
@EnableJpaRepositories("dogs.repo")
@EnableAutoConfiguration
@EntityScan("dogs.model")
public class DogsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DogsBackendApplication.class, args);
	}

}
