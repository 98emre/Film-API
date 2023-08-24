package experis.academy.filmapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Movie API", description = "Simple API to show characters, the movies they play in, and the franchises those movies belong to.", version = "1.0"))
public class FilmapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmapiApplication.class, args);
	}

}
