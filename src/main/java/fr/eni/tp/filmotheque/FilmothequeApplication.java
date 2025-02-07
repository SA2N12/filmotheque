package fr.eni.tp.filmotheque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import fr.eni.tp.filmotheque.controller.FilmController;

@SpringBootApplication
public class FilmothequeApplication {

	public static void main(String[] args) {
		
		ApplicationContext context = SpringApplication.run(FilmothequeApplication.class, args);
		
		FilmController controller = context.getBean(FilmController.class);

	}

}
