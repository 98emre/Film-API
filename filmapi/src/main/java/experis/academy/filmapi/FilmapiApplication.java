package experis.academy.filmapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling

public class FilmapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmapiApplication.class, args);
	}

}
