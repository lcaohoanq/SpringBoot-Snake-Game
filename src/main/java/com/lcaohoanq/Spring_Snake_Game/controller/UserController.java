package com.lcaohoanq.Spring_Snake_Game.controller;

import com.lcaohoanq.Spring_Snake_Game.dto.AbstractResponse;
import com.lcaohoanq.Spring_Snake_Game.dto.request.UserRegisterRequest;
import com.lcaohoanq.Spring_Snake_Game.dto.request.UserUpdatePasswordRequest;
import com.lcaohoanq.Spring_Snake_Game.dto.response.JwtResponse;
import com.lcaohoanq.Spring_Snake_Game.dto.request.UserLoginRequest;
import com.lcaohoanq.Spring_Snake_Game.dto.request.UserRegisterRequestFull;
import com.lcaohoanq.Spring_Snake_Game.dto.response.UserResponse;
import com.lcaohoanq.Spring_Snake_Game.exception.MethodArgumentNotValidException;
import com.lcaohoanq.Spring_Snake_Game.exception.UserNotFoundException;
import com.lcaohoanq.Spring_Snake_Game.entity.User;
import com.lcaohoanq.Spring_Snake_Game.repository.UserRepository;
import com.lcaohoanq.Spring_Snake_Game.util.PBKDF2;
import com.lcaohoanq.Spring_Snake_Game.util.ValidateUtils;
import jakarta.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private PBKDF2 pbkdf2;

    @GetMapping("/users")
    List<User> all() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    User getById(@PathVariable Long id) {

        return userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping("/users/oauth2/callback/google")
    @Async
    public CompletableFuture<ResponseEntity<UserResponse>> createNewAccountGoogle(
        @Valid @RequestBody UserRegisterRequest newUser, BindingResult bindingResult) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                if (bindingResult.hasErrors()) {
                    log.error("Validation failed for user at: {}", newUser.getCreated_at());
                    throw new MethodArgumentNotValidException(bindingResult);
                }

                if ((newUser.getEmail() == null || newUser.getEmail().isEmpty())) {
                    throw new IllegalArgumentException("Must provide email when login with Google");
                } else {
                    boolean emailExists = userRepository.findAll().stream()
                        .anyMatch(user -> newUser.getEmail().equals(user.getEmail()));
                    if (emailExists) {
                        log.error("Email already Login with Google: {}", newUser.getEmail());
                        return new ResponseEntity<>(new UserResponse("Login Google successfully"),
                            HttpStatus.OK);
                    }
                }

                // Save the user to the repository
                User user = new User();
                user.setId(newUser.getId());
                user.setEmail(newUser.getEmail());
                user.setPhone(newUser.getPhone());
                user.setFirstName(newUser.getFirstName());
                user.setLastName(newUser.getLastName());
                user.setPassword(newUser.getPassword());
                user.setAddress(newUser.getAddress());
                user.setBirthday(newUser.getBirthday());
                user.setGender(newUser.getGender());
                user.setRole(newUser.getRole());
                user.setStatus(newUser.getStatus());
                user.setCreated_at(newUser.getCreated_at());
                user.setUpdated_at(newUser.getUpdated_at());
                user.setAvatar_url(newUser.getAvatar_url());
//                user.setSubscription(newUser.getSubscription());

                userRepository.save(user);
                return new ResponseEntity<>(new UserResponse("Login Google successfully"),
                    HttpStatus.OK);
            }  catch(Exception e){
                log.error("An error occurred while creating a new user: {}", e.getMessage());
                return new ResponseEntity<>(new UserResponse(e.getMessage()),
                    HttpStatus.BAD_REQUEST);
            }
        });
    }

    @PostMapping("/users/register")
    @Async
    public CompletableFuture<ResponseEntity<UserResponse>> createNew(
        @Valid @RequestBody UserRegisterRequest newUser, BindingResult bindingResult) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                if (bindingResult.hasErrors()) {
                    log.error("Validation failed for user at: {}", newUser.getCreated_at());
                    throw new MethodArgumentNotValidException(bindingResult);
                }

                if (StringUtils .isBlank(newUser.getEmail()) && StringUtils.isBlank(newUser.getPhone())) {
                    throw new IllegalArgumentException("Either email or phone must be provided.");
                } else {
                    boolean emailExists = false;
                    boolean phoneExists = false;

                    // Assuming userRepository has methods to find by email and phone
                    if (StringUtils.isNotBlank(newUser.getEmail())) {
                        emailExists = userRepository.findByEmail(newUser.getEmail()) != null;
                    }
                    if (StringUtils.isNotBlank(newUser.getPhone())) {
                        phoneExists = userRepository.findByPhone(newUser.getPhone()) != null;
                    }

                    if (emailExists) {
                        log.error("Email already exists: {}", newUser.getEmail());
                        return new ResponseEntity<>(new UserResponse("Email already exists"), HttpStatus.BAD_REQUEST);
                    }
                    if (phoneExists) {
                        log.error("Phone number already exists: {}", newUser.getPhone());
                        return new ResponseEntity<>(new UserResponse("Phone number already exists"), HttpStatus.BAD_REQUEST);
                    }
                }


                // Hash the password before saving the user
                newUser.setPassword(new PBKDF2().hash(newUser.getPassword().toCharArray()));
                log.info("Creating new user at: {}", newUser.getCreated_at());

                // Save the user to the repository
                User user = new User();
                user.setId(newUser.getId());
                user.setEmail(newUser.getEmail());
                user.setPhone(newUser.getPhone());
                user.setFirstName(newUser.getFirstName());
                user.setLastName(newUser.getLastName());
                user.setPassword(newUser.getPassword());
                user.setAddress(newUser.getAddress());
                user.setBirthday(newUser.getBirthday());
                user.setGender(newUser.getGender());
                user.setRole(newUser.getRole());
                user.setStatus(newUser.getStatus());
                user.setCreated_at(newUser.getCreated_at());
                user.setUpdated_at(newUser.getUpdated_at());
                user.setAvatar_url(newUser.getAvatar_url());
//                user.setSubscription(newUser.getSubscription());

                System.out.println("Data: " + user);

                userRepository.save(user);

                return new ResponseEntity<>(new UserResponse("Register successfully"),
                    HttpStatus.OK);
            }  catch(Exception e){
                log.error("An error occurred while creating a new user: {}", e.getMessage());
                return new ResponseEntity<>(new UserResponse(e.getMessage()),
                    HttpStatus.BAD_REQUEST);
            }
        });
    }

    @PostMapping("/users/login")
    ResponseEntity<AbstractResponse> login(@Valid @RequestBody UserLoginRequest user,
        BindingResult bindingResult) {
        User userFound;

        if (bindingResult.hasErrors()) {
            log.error("Validation failed for user when login");
            throw new MethodArgumentNotValidException(bindingResult);
        }

        // Check email or phone
        String emailOrPhone = user.getEmail_phone();
        boolean isEmail = ValidateUtils.checkTypeAccount(emailOrPhone);

        userFound = isEmail ? userRepository.findByEmail(emailOrPhone) : userRepository.findByPhone(emailOrPhone);

        if (userFound == null) {
            String notFoundMessage = isEmail ? "Email not found: " : "Phone number not found: ";
            log.error("{}{}", notFoundMessage, emailOrPhone);
            String responseMessage = isEmail ? "Email not found" : "Phone number not found";
            return new ResponseEntity<>(new UserResponse(responseMessage), HttpStatus.BAD_REQUEST);
        }

        if (!ValidateUtils.authenticate(user.getPassword(), userFound.getPassword())) {
            log.error("Password wrong: {}", user.getEmail_phone());
            return new ResponseEntity<>(new UserResponse("Password wrong"),
                HttpStatus.BAD_REQUEST);
        }

        log.info("Login successfully: {}", user.getEmail_phone());

        // Generate tokens
        JwtResponse jwtResponse = new JwtResponse("accessToken", "refreshToken");

        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }


    @DeleteMapping("/users/{id}")
    void delete(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @PutMapping("/users/{id}")
    User updateOrCreate(@RequestBody User newUser, @PathVariable Long id) {

        return userRepository.findById(id)
            .map(item -> {
                item.setFirstName(newUser.getFirstName());
                return userRepository.save(item);
            })
            .orElseGet(() -> {
                newUser.setId(id);
                return userRepository.save(newUser);
            });
    }

    //update user password
    @PostMapping("/users/updatePassword")
    public ResponseEntity<UserResponse> updatePassword(@RequestBody UserUpdatePasswordRequest user) {
        User data;

        if(ValidateUtils.checkTypeAccount(user.getIdentifier())){
            data = userRepository.findByEmail(user.getIdentifier());
        }else{
            data = userRepository.findByPhone(user.getIdentifier());
        }

        if(data == null){
            return new ResponseEntity<>(new UserResponse("User not found"),
                HttpStatus.BAD_REQUEST);
        }

        data.setPassword(user.getNewPassword());
        userRepository.save(data);

        return new ResponseEntity<>(new UserResponse("Password updated successfully"),
            HttpStatus.OK);
    }

}
