package com.lcaohoanq.Spring_Snake_Game;

import com.lcaohoanq.Spring_Snake_Game.model.Score;
import com.lcaohoanq.Spring_Snake_Game.model.User;
import com.lcaohoanq.Spring_Snake_Game.repository.ScoreRepository;
import com.lcaohoanq.Spring_Snake_Game.repository.UserRepository;
import io.github.cdimascio.dotenv.Dotenv;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.lcaohoanq.Spring_Snake_Game",
    "com.lcaohoanq.Spring_Snake_Game.listener"})
public class SpringSnakeGameApplication implements CommandLineRunner {

    @Autowired
    private ScoreRepository scoreRepository;

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
        User user1 = new User(1L, "hoang", "luu", "hoangdz1604@gmail.com",
            "0987654321", "Iloveyou123!", 1, 1, LocalDateTime.now().toString(),
            LocalDateTime.now().toString(), null, 0);
        User user2 = new User(2L, "duong", "nguyen", "manhduonglhp4@gmail.com",
            "0987654322", "Iloveyou123!", 1, 1, LocalDateTime.now().toString(),
            LocalDateTime.now().toString(), null, 0);
        User user3 = new User(3L, "thu", "minh", "thuttass@fpt.edu.vn",
            "0987654323", "Iloveyou123!", 1, 1, LocalDateTime.now().toString(),
            LocalDateTime.now().toString(), null, 0);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        Score score1 = new Score(10, 90, LocalDateTime.now().toString(),
            LocalDateTime.now().toString(), user1);
        Score score2 = new Score(0, 9, LocalDateTime.now().toString(),
            LocalDateTime.now().toString(), user2);

        scoreRepository.save(score1);
        scoreRepository.save(score2);

    }
}
