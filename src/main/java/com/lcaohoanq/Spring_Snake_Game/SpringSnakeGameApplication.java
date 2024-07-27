package com.lcaohoanq.Spring_Snake_Game;

import com.lcaohoanq.Spring_Snake_Game.model.User;
import com.lcaohoanq.Spring_Snake_Game.repository.UserRepository;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.lcaohoanq.Spring_Snake_Game", "com.lcaohoanq.Spring_Snake_Game.listener"})
public class SpringSnakeGameApplication implements CommandLineRunner {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.load();

		// Now you can access environment variables
		String dbUsername = dotenv.get("DB_USERNAME");
		String dbPassword = dotenv.get("DB_PASSWORD");

		SpringApplication.run(SpringSnakeGameApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new User(1L, "hoang", "luu", "hoangdz1604@gmail.com", "0987654321", "lcaohoanq", "Iloveyou123!"));
		userRepository.save(new User(2L, "duong", "manh", "manhduonglhp4@gmail.com", "0987654321", "duongnm", "Iloveyou123!"));
	}
}
