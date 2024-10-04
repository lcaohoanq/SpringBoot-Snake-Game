package com.lcaohoanq.Spring_Snake_Game.listener;

import com.github.javafaker.Faker;
import com.lcaohoanq.Spring_Snake_Game.model.Role;
import com.lcaohoanq.Spring_Snake_Game.model.Score;
import com.lcaohoanq.Spring_Snake_Game.model.SocialAccount;
import com.lcaohoanq.Spring_Snake_Game.model.Status;
import com.lcaohoanq.Spring_Snake_Game.model.User;
import com.lcaohoanq.Spring_Snake_Game.enums.SocialAccountProviderEnum;
import com.lcaohoanq.Spring_Snake_Game.enums.UserGenderEnum;
import com.lcaohoanq.Spring_Snake_Game.enums.UserRoleEnum;
import com.lcaohoanq.Spring_Snake_Game.enums.UserStatusEnum;
import com.lcaohoanq.Spring_Snake_Game.repository.RoleRepository;
import com.lcaohoanq.Spring_Snake_Game.repository.ScoreRepository;
import com.lcaohoanq.Spring_Snake_Game.repository.SocialAccountRepository;
import com.lcaohoanq.Spring_Snake_Game.repository.StatusRepository;
import com.lcaohoanq.Spring_Snake_Game.repository.UserRepository;
import com.lcaohoanq.Spring_Snake_Game.service.UserService;
import com.lcaohoanq.Spring_Snake_Game.util.PBKDF2;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Profile("default")
@RequiredArgsConstructor
public class DemoDataRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ScoreRepository scoreRepository;
    private final RoleRepository roleRepository;
    private final StatusRepository statusRepository;
    private final SocialAccountRepository socialAccountRepository;
    private final UserService userService;

    private Role getOrCreateRole(UserRoleEnum userRoleEnum) {
        Role role = roleRepository.findByRoleName(userRoleEnum);
        if (role == null) {
            role = new Role(userRoleEnum.getRole(), userRoleEnum);
            roleRepository.save(role);
        }
        return role;
    }

    private Status getOrCreateStatusField(UserStatusEnum userStatus) {
        Status status = statusRepository.findByStatusName(userStatus);
        if (status == null) {
            status = new Status(userStatus.getStatus(), userStatus);
            statusRepository.save(status);
        }
        return status;
    }

    private User getOrCreateUser(Long id, String firstName, String lastName, String email,
        String phone, String password, String birthday, String address, UserGenderEnum gender,
        Role role, Status status, LocalDateTime createdAt, LocalDateTime updatedAt,
        String avatarUrl,
        int subscription) {
        return userRepository.findById(id).orElseGet(() -> {
            User user = new User(id, firstName, lastName, email, phone, password, birthday, address,
                gender, role, status, createdAt, updatedAt, avatarUrl, subscription);
            // Ensure the user is added to the role's user set
            role.getUsers().add(user);
            status.getUsers().add(user);
            userRepository.save(user);
            return user;
        });
    }

    private void updateUserSocialAccountId(User user, SocialAccount socialAccount) {
        if (socialAccount.getProvider() == SocialAccountProviderEnum.GOOGLE) {
            user.setGoogle_account_id(1);
        } else if (socialAccount.getProvider() == SocialAccountProviderEnum.FACEBOOK) {
            user.setFacebook_account_id(1);
        }
        userRepository.save(user);
    }


    @Override
    public void run(String... args) {
        try {
            log.info("Starting DemoDataRunner...");

            String hashedPassword = new PBKDF2().hash("Iloveyou123!".toCharArray());

            Faker faker = new Faker();

            // Retrieve or create roles and statuses
            Role userRole = getOrCreateRole(UserRoleEnum.USER);
            Role employeeRole = getOrCreateRole(UserRoleEnum.EMPLOYEE);
            Role userGoldRole = getOrCreateRole(UserRoleEnum.USER_GOLD);
            Role userPremiumRole = getOrCreateRole(UserRoleEnum.USER_PREMIUM);
            Role[] possibleRoles = {userRole, employeeRole, userGoldRole, userPremiumRole};

            Status verified = getOrCreateStatusField(UserStatusEnum.VERIFIED);
            Status unverified = getOrCreateStatusField(UserStatusEnum.UNVERIFIED);
            Status banned = getOrCreateStatusField(UserStatusEnum.BANNED);
            Status[] possibleStatuses = {verified, unverified, banned};

            // Create users 11 to 500 with Faker
            for (long i = 1; i <= 5; i++) {
                // Decide randomly whether to make email or phone null, but not both
//                boolean makeEmailNull = faker.bool().bool();
//                boolean makePhoneNull = !makeEmailNull; // Ensure one is null and the other is not
//
//                String email = makeEmailNull ? null : faker.internet().emailAddress();
//                String phone = makePhoneNull ? null : "0" + faker.number().numberBetween(3, 9) + faker.number().digits(8);

                String email = faker.internet().emailAddress();
                String phone = "0" + faker.number().numberBetween(3, 9) + faker.number().digits(8);

                if (userService.existsByEmail(email) || userService.existsByPhone(phone)) {
                    continue;
                }
                String firstName = faker.name().firstName();
                String lastName = faker.name().lastName();
                String birthday = faker.date().birthday(18, 65).toString();
                String address = faker.address().fullAddress();
//                byte[] avatarUrl = AvatarConverter.convertAvatarUrlToByteArray(faker.avatar().image());
                String avatarUrl = faker.avatar().image();
                UserGenderEnum gender =
                    faker.bool().bool() ? UserGenderEnum.MALE : UserGenderEnum.FEMALE;

                // Randomly select a status for each user
                Status randomStatus = possibleStatuses[faker.random()
                    .nextInt(possibleStatuses.length)];

                // Randomly select a role for each user
                Role randomeRole = possibleRoles[faker.random().nextInt(possibleRoles.length)];

                User user = getOrCreateUser(
                    i, firstName, lastName, email, phone, hashedPassword,
                    birthday, address, gender, randomeRole, randomStatus,
                    LocalDateTime.now(), LocalDateTime.now(), avatarUrl,
                    faker.number().numberBetween(0, 2) //2 is exclusive
                );

                userRepository.save(user);

                // Optionally create related entities like Score or SocialAccount here
                Score score = new Score(faker.number().randomDigit(),
                    faker.number().numberBetween(0, 100), LocalDateTime.now(),
                    LocalDateTime.now(), user);
                scoreRepository.save(score);

                // Randomly select a social account provider
                SocialAccountProviderEnum[] providers = SocialAccountProviderEnum.values();
                SocialAccountProviderEnum randomProvider = providers[faker.random()
                    .nextInt(providers.length)];

                SocialAccount socialAccount = new SocialAccount(randomProvider,
                    randomProvider.getProvider(), user.getEmail(),
                    user.getFirstName(), user);
                socialAccountRepository.save(socialAccount);

                // Update user with social account ID if necessary
                updateUserSocialAccountId(user, socialAccount);
            }

            Role adminRole = getOrCreateRole(UserRoleEnum.ADMIN);
            Status verifiedStatus = getOrCreateStatusField(UserStatusEnum.VERIFIED);
            User myAccount = new User(6L, "hoang", "luu", "hoangdz1604@gmail.com", null, hashedPassword, "1999-07-01", "Duong Vo Van Hat, Ho Chi Minh City", UserGenderEnum.MALE, adminRole, verifiedStatus, LocalDateTime.now(), LocalDateTime.now(), null, 1);
            userRepository.save(myAccount);

            log.info("Demo data created successfully");
        } catch (Exception e) {
            log.error("Error while creating demo data", e);
        }
    }

}