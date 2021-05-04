package ru.hse.diploma;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Класс приложения Diploma Crawler API.
 */
@SpringBootApplication
public class DiplomaCrawlerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiplomaCrawlerApiApplication.class, args);
	}

}
