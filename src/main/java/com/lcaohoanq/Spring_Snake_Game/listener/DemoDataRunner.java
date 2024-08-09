package com.lcaohoanq.Spring_Snake_Game.listener;

import com.lcaohoanq.Spring_Snake_Game.entity.Role;
import com.lcaohoanq.Spring_Snake_Game.entity.Score;
import com.lcaohoanq.Spring_Snake_Game.entity.Status;
import com.lcaohoanq.Spring_Snake_Game.entity.User;
import com.lcaohoanq.Spring_Snake_Game.enums.UserGenderEnum;
import com.lcaohoanq.Spring_Snake_Game.enums.UserRoleEnum;
import com.lcaohoanq.Spring_Snake_Game.enums.UserStatusEnum;
import com.lcaohoanq.Spring_Snake_Game.repository.RoleRepository;
import com.lcaohoanq.Spring_Snake_Game.repository.ScoreRepository;
import com.lcaohoanq.Spring_Snake_Game.repository.StatusRepository;
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
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private StatusRepository statusRepository;

    private Role getOrCreateRole(UserRoleEnum userRoleEnum) {
        Role role = roleRepository.findByRoleName(userRoleEnum);
        if (role == null) {
            role = new Role(userRoleEnum.getRole(), userRoleEnum);
            roleRepository.save(role);
        }
        return role;
    }

    private Status getOrCreateStatusField(UserStatusEnum userStatus){
        Status status = statusRepository.findByStatusName(userStatus);
        if(status == null){
            status = new Status(userStatus.getStatus(), userStatus);
            statusRepository.save(status);
        }
        return status;
    }

    private User getOrCreateUser(Long id, String firstName, String lastName, String email, String phone, String password, String birthday, String address, UserGenderEnum gender, Role role, Status status, String createdAt, String updatedAt, byte[] avatarUrl, int subscription) {
        return userRepository.findById(id).orElseGet(() -> {
            User user = new User(id, firstName, lastName, email, phone, password, birthday, address, gender, role, status, createdAt, updatedAt, avatarUrl, subscription);
            // Ensure the user is added to the role's user set
            role.getUsers().add(user);
            status.getUsers().add(user);
            userRepository.save(user);
            return user;
        });
    }

    @Override
    public void run(String... args) {
        String hashedPassword = new PBKDF2().hash("Iloveyou123!".toCharArray());

        Role adminRole = getOrCreateRole(UserRoleEnum.ADMIN);
        Role userRole = getOrCreateRole(UserRoleEnum.USER);
        Role employeeRole = getOrCreateRole(UserRoleEnum.EMPLOYEE);
        Role userGoldRole = getOrCreateRole(UserRoleEnum.USER_GOLD);
        Role userPremiumRole = getOrCreateRole(UserRoleEnum.USER_PREMIUM);

        Status unverified = getOrCreateStatusField(UserStatusEnum.UNVERIFIED);
        Status verified = getOrCreateStatusField(UserStatusEnum.VERIFIED);
        Status banned = getOrCreateStatusField(UserStatusEnum.BANNED);

        User user1 = getOrCreateUser(1L, "hoang", "luu", "hoangdz1604@gmail.com", null, hashedPassword, "1999-07-01", "Duong Vo Van Hat, Ho Chi Minh City", UserGenderEnum.MALE, adminRole, unverified, LocalDateTime.now().toString(), LocalDateTime.now().toString(), null, 1);
        User user2 = getOrCreateUser(2L, "duong", "nguyen", null, "0987654322", hashedPassword, "1999-07-01", "Thanh Pho Tam Ki, Tinh Quang Nam", UserGenderEnum.MALE, userRole, verified, LocalDateTime.now().toString(), LocalDateTime.now().toString(), null, 0);
        User user3 = getOrCreateUser(3L, "thu", "minh", null, "0987654323", hashedPassword, "1999-07-01", "Quan 9, Thu Duc", UserGenderEnum.FEMALE, userRole, banned, LocalDateTime.now().toString(), LocalDateTime.now().toString(), null, 0);
        User user4 = getOrCreateUser(4L, "nhu", "minh", "mnhw.0612@gmail.com", null, hashedPassword, "2006-12-06", "Nguyen Tat Thanh St, Da Nang City", UserGenderEnum.FEMALE, userRole, banned, LocalDateTime.now().toString(), LocalDateTime.now().toString(), null, 0);
        User user5 = getOrCreateUser(5L, "chau", "bao", "doanthibaochau0410@gmail.com", null, hashedPassword, "2004-07-01", "Hoa Khanh", UserGenderEnum.FEMALE, employeeRole, banned, LocalDateTime.now().toString(), LocalDateTime.now().toString(), null, 0);
        User user6 = getOrCreateUser(6L, "Duong", "Manh", "manhduonglhp4@gmail.com", null, hashedPassword, "2004-07-01", "Hoa Khanh", UserGenderEnum.MALE, userGoldRole, unverified, LocalDateTime.now().toString(), LocalDateTime.now().toString(), null, 0);
        User user7 = getOrCreateUser(7L, "Tram", "Minh", "tramsowavy@gmail.com", null, hashedPassword, "2004-07-01", "Hoa Xuan, Hoa Vang, Da Nang", UserGenderEnum.FEMALE, userPremiumRole, verified, LocalDateTime.now().toString(), LocalDateTime.now().toString(), null, 0);
        User user8 = getOrCreateUser(8L, "きむら", "山口", "test1@yahoo.com", null, hashedPassword, "2004-07-01", "Tokyo, Japan", UserGenderEnum.FEMALE, userPremiumRole, verified, LocalDateTime.now().toString(), LocalDateTime.now().toString(), null, 0);
        User user9 = getOrCreateUser(9L, "えいみ", "ふかだ", "test2@gmail.com", null, hashedPassword, "2004-07-01", "Nakayama, Japan", UserGenderEnum.MALE, userPremiumRole, verified, LocalDateTime.now().toString(), LocalDateTime.now().toString(), null, 0);
        User user10 = getOrCreateUser(10L, "Hai", "Luu Van", "test3@outlook.com", null, hashedPassword, "2004-07-01", "Tuy Loan, Hoa Phong", UserGenderEnum.MALE, userPremiumRole, verified, LocalDateTime.now().toString(), LocalDateTime.now().toString(), null, 0);

        Score score1 = new Score(10, 90, LocalDateTime.now().toString(), LocalDateTime.now().toString(), user1);
        Score score2 = new Score(1, 1, LocalDateTime.now().toString(), LocalDateTime.now().toString(), user2);
        Score score3 = new Score(2, 20, LocalDateTime.now().toString(), LocalDateTime.now().toString(), user3);
        Score score4 = new Score(3, 12, LocalDateTime.now().toString(), LocalDateTime.now().toString(), user4);
        Score score5 = new Score(4, 9, LocalDateTime.now().toString(), LocalDateTime.now().toString(), user5);
        Score score6 = new Score(5, 56, LocalDateTime.now().toString(), LocalDateTime.now().toString(), user6);
        Score score7 = new Score(6, 9, LocalDateTime.now().toString(), LocalDateTime.now().toString(), user7);
        Score score8 = new Score(7, 34, LocalDateTime.now().toString(), LocalDateTime.now().toString(), user8);
        Score score9 = new Score(8, 9, LocalDateTime.now().toString(), LocalDateTime.now().toString(), user9);
        Score score10 = new Score(9, 9, LocalDateTime.now().toString(), LocalDateTime.now().toString(), user10);

        scoreRepository.save(score1);
        scoreRepository.save(score2);
        scoreRepository.save(score3);
        scoreRepository.save(score4);
        scoreRepository.save(score5);
        scoreRepository.save(score6);
        scoreRepository.save(score7);
        scoreRepository.save(score8);
        scoreRepository.save(score9);
        scoreRepository.save(score10);

    }

}