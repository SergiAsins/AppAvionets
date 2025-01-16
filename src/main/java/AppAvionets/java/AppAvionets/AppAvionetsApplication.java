package AppAvionets.java.AppAvionets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import AppAvionets.java.AppAvionets.controllers.UserController;

@SpringBootApplication
public class AppAvionetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppAvionetsApplication.class, args);
		System.out.println("Starts Fly");
	}
}
