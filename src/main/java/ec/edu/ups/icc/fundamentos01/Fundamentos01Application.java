package ec.edu.ups.icc.fundamentos01;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RestController
@RequestMapping("/api/users")
@SpringBootApplication
public class Fundamentos01Application {

	public static void main(String[] args) {
		SpringApplication.run(Fundamentos01Application.class, args);
	}

}
