package com.lcaohoanq.Spring_Snake_Game;

import com.lcaohoanq.Spring_Snake_Game.entity.Score;
import com.lcaohoanq.Spring_Snake_Game.entity.User;
import com.lcaohoanq.Spring_Snake_Game.enums.UserGenderEnum;
import com.lcaohoanq.Spring_Snake_Game.enums.UserRoleEnum;
import com.lcaohoanq.Spring_Snake_Game.enums.UserStatusEnum;
import com.lcaohoanq.Spring_Snake_Game.repository.ScoreRepository;
import com.lcaohoanq.Spring_Snake_Game.repository.UserRepository;
import com.lcaohoanq.Spring_Snake_Game.util.PBKDF2;
import io.github.cdimascio.dotenv.Dotenv;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
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

        PBKDF2 pbkdf2 = new PBKDF2();

        User user1 = new User(1L, "hoang", "luu", "hoangdz1604@gmail.com",
            null, pbkdf2.hash("Iloveyou123!".toCharArray()), "1999-07-01",
            "Duong Vo Van Hat, Ho Chi Minh City", UserGenderEnum.MALE,
            UserRoleEnum.ADMIN, UserStatusEnum.UNVERIFIED,
            LocalDateTime.now().toString(),
            LocalDateTime.now().toString(), null, 1);
        User user2 = new User(2L, "duong", "nguyen", null,
            "0987654322", pbkdf2.hash("Iloveyou123!".toCharArray()), "1999-07-01",
            "Thanh Pho Tam Ki, Tinh Quang Nam", UserGenderEnum.MALE, UserRoleEnum.USER,
            UserStatusEnum.VERIFIED,
            LocalDateTime.now().toString(),
            LocalDateTime.now().toString(), null, 0);
        User user3 = new User(3L, "thu", "minh", null,
            "0987654323", pbkdf2.hash("Iloveyou123!".toCharArray()), "1999-07-01",
            "Quan 9, Thu Duc", UserGenderEnum.FEMALE, UserRoleEnum.USER,
            UserStatusEnum.BANNED,
            LocalDateTime.now().toString(),
            LocalDateTime.now().toString(), null, 0);
        User user4 = new User(4L, "nhu", "minh", "mnhw.0612@gmail.com",
            null, pbkdf2.hash("Iloveyou123!".toCharArray()), "2006-12-06",
            "Nguyen Tat Thanh St, Da Nang City", UserGenderEnum.FEMALE, UserRoleEnum.USER,
            UserStatusEnum.BANNED,
            LocalDateTime.now().toString(),
            LocalDateTime.now().toString(), null, 0);
        User user5 = new User(5L, "chau", "bao", "doanthibaochau0410@gmail.com",
            null, pbkdf2.hash("Iloveyou123!".toCharArray()), "2004-07-01", "Hoa Khanh",
            UserGenderEnum.FEMALE,
            UserRoleEnum.USER,
            UserStatusEnum.BANNED,
            LocalDateTime.now().toString(),
            LocalDateTime.now().toString(), null, 0);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);

        Score score1 = new Score(10, 90, LocalDateTime.now().toString(),
            LocalDateTime.now().toString(), user1);
        Score score2 = new Score(0, 9, LocalDateTime.now().toString(),
            LocalDateTime.now().toString(), user2);
        Score score3 = new Score(0, 9, LocalDateTime.now().toString(),
            LocalDateTime.now().toString(), user3);
        Score score4 = new Score(0, 9, LocalDateTime.now().toString(),
            LocalDateTime.now().toString(), user4);

        scoreRepository.save(score1);
        scoreRepository.save(score2);
        scoreRepository.save(score3);
        scoreRepository.save(score4);

    }
}
