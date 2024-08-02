package com.lcaohoanq.Spring_Snake_Game.listener;

import com.lcaohoanq.Spring_Snake_Game.entity.Score;
import com.lcaohoanq.Spring_Snake_Game.entity.User;
import com.lcaohoanq.Spring_Snake_Game.enums.UserGenderEnum;
import com.lcaohoanq.Spring_Snake_Game.enums.UserRoleEnum;
import com.lcaohoanq.Spring_Snake_Game.enums.UserStatusEnum;
import com.lcaohoanq.Spring_Snake_Game.repository.ScoreRepository;
import com.lcaohoanq.Spring_Snake_Game.repository.UserRepository;
import com.lcaohoanq.Spring_Snake_Game.util.PBKDF2;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoDataRunner implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ScoreRepository scoreRepository;

    @Override
    public void run(String... args) {

        String hashedPassword = new PBKDF2().hash("Iloveyou123!".toCharArray());

        User user1 = new User
            (
                1L,
                "hoang",
                "luu",
                "hoangdz1604@gmail.com",
                null,
                hashedPassword,
                "1999-07-01",
                "Duong Vo Van Hat, Ho Chi Minh City",
                UserGenderEnum.MALE,
                UserRoleEnum.ADMIN,
                UserStatusEnum.UNVERIFIED,
                LocalDateTime.now().toString(),
                LocalDateTime.now().toString(),
                null,
                1
            );
        User user2 = new User
            (2L,
                "duong",
                "nguyen",
                null,
                "0987654322",
                hashedPassword,
                "1999-07-01",
                "Thanh Pho Tam Ki, Tinh Quang Nam",
                UserGenderEnum.MALE,
                UserRoleEnum.USER,
                UserStatusEnum.VERIFIED,
                LocalDateTime.now().toString(),
                LocalDateTime.now().toString(),
                null,
                0
            );
        User user3 = new User
            (3L,
                "thu",
                "minh",
                null,
                "0987654323",
                hashedPassword,
                "1999-07-01",
                "Quan 9, Thu Duc",
                UserGenderEnum.FEMALE,
                UserRoleEnum.USER,
                UserStatusEnum.BANNED,
                LocalDateTime.now().toString(),
                LocalDateTime.now().toString(),
                null,
                0
            );
        User user4 = new User
            (4L,
                "nhu",
                "minh",
                "mnhw.0612@gmail.com",
                null, hashedPassword,
                "2006-12-06",
                "Nguyen Tat Thanh St, Da Nang City",
                UserGenderEnum.FEMALE,
                UserRoleEnum.USER,
                UserStatusEnum.BANNED,
                LocalDateTime.now().toString(),
                LocalDateTime.now().toString(),
                null,
                0
            );
        User user5 = new User
            (5L,
                "chau",
                "bao",
                "doanthibaochau0410@gmail.com",
                null, hashedPassword,
                "2004-07-01",
                "Hoa Khanh",
                UserGenderEnum.FEMALE,
                UserRoleEnum.USER,
                UserStatusEnum.BANNED,
                LocalDateTime.now().toString(),
                LocalDateTime.now().toString(),
                null,
                0
            );

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);

        Score score1 = new Score
            (
                10,
                90,
                LocalDateTime.now().toString(),
                LocalDateTime.now().toString(),
                user1
            );
        Score score2 = new Score
            (
                0,
                9,
                LocalDateTime.now().toString(),
                LocalDateTime.now().toString(),
                user2
            );
        Score score3 = new Score
            (
                0,
                9,
                LocalDateTime.now().toString(),
                LocalDateTime.now().toString(),
                user3
            );
        Score score4 = new Score
            (
                0,
                9,
                LocalDateTime.now().toString(),
                LocalDateTime.now().toString(),
                user4
            );

        scoreRepository.save(score1);
        scoreRepository.save(score2);
        scoreRepository.save(score3);
        scoreRepository.save(score4);

    }

}
