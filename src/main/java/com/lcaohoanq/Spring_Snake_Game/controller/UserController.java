package com.lcaohoanq.Spring_Snake_Game.controller;

import com.lcaohoanq.Spring_Snake_Game.dto.ApiResponse;
import com.lcaohoanq.Spring_Snake_Game.dto.JwtResponse;
import com.lcaohoanq.Spring_Snake_Game.dto.UserLoginRequest;
import com.lcaohoanq.Spring_Snake_Game.dto.UserRegisterRequest;
import com.lcaohoanq.Spring_Snake_Game.dto.UserResponse;
import com.lcaohoanq.Spring_Snake_Game.exception.MethodArgumentNotValidException;
import com.lcaohoanq.Spring_Snake_Game.exception.UserNotFoundException;
import com.lcaohoanq.Spring_Snake_Game.entity.User;
import com.lcaohoanq.Spring_Snake_Game.repository.UserRepository;
import com.lcaohoanq.Spring_Snake_Game.util.PBKDF2;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

    private List<User> demoList = List.of(
        new User(10L, "minh", "nhu", "tranthiminhnhu@gmail.com",
            "0905288699", "$31$16$GcvQCMYKe7MvCzsGk-gq8nsAMqahchPHetFCtLEqhZs", "1999-07-01", "Ho Chi Minh", 1, 1,
            LocalDateTime.now().toString(),
            LocalDateTime.now().toString(), null, 0),
        new User(11L, "anh", "hao", "macanhhao@gmail.com",
        "0905656365", "$31$16$GcvQCMYKe7MvCzsGk-gq8nsAMqahchPHetFCtLEqhZs", "1999-07-01", "Ho Chi Minh", 1, 1,
        LocalDateTime.now().toString(),
            LocalDateTime.now().toString(), null, 0)
    );

    @GetMapping("/users")
    List<User> all() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    User getById(@PathVariable Long id) {

        return userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id)) ;
    }

    @PostMapping("/users/register")
    ResponseEntity<UserResponse> createNew(@Valid @RequestBody UserRegisterRequest newUser, BindingResult bindingResult) {
        PBKDF2 pbkdf2 = new PBKDF2();

        if (bindingResult.hasErrors()) {
            log.error("Validation failed for user at: {}", newUser.getCreated_at());
            throw new MethodArgumentNotValidException(bindingResult);
        }

        boolean emailExists = demoList.stream()
            .anyMatch(user -> user.getEmail().equals(newUser.getEmail()));

        boolean phoneExists = demoList.stream()
            .anyMatch(user -> user.getPhone().equals(newUser.getPhone()));

        if (emailExists) {
            log.error("Email already registered: {}", newUser.getEmail());
            return new ResponseEntity<>(new UserResponse("Email already registered", "ERROR"), HttpStatus.BAD_REQUEST);
        } else if(phoneExists) {
            log.error("Phone number already registered: {}", newUser.getPhone());
            return new ResponseEntity<>(new UserResponse("Phone number already registered", "ERROR"), HttpStatus.BAD_REQUEST);
        }

        // Hash the password before saving the user
        newUser.setPassword(pbkdf2.hash(newUser.getPassword().toCharArray()));
        log.info("Creating new user at: {}", newUser.getCreated_at());
        // Note: Normally you would save the user to the repository here
        return new ResponseEntity<>(new UserResponse("Register successfully", "OK"), HttpStatus.OK);
    }

    @PostMapping("/users/login")
    ResponseEntity<ApiResponse> login(@Valid @RequestBody UserLoginRequest user, BindingResult bindingResult) {
        User userFound = null;
        PBKDF2 pbkdf2 = new PBKDF2();
        if(bindingResult.hasErrors()) {
            log.error("Validation failed for user when login");
            throw new MethodArgumentNotValidException(bindingResult);
        }

        // Check email or phone
        if(user.getEmail_phone().contains("@")) {
            userFound = demoList.stream()
                .filter(u -> u.getEmail().equals(user.getEmail_phone()))
                .findFirst()
                .orElse(null);

            if (userFound == null) {
                log.error("Email not found: {}", user.getEmail_phone());
                return new ResponseEntity<>(new UserResponse("Email not found", "ERROR"), HttpStatus.BAD_REQUEST);
            }
        } else {
            userFound = demoList.stream()
                .filter(u -> u.getPhone().equals(user.getEmail_phone()))
                .findFirst()
                .orElse(null);

            if (userFound == null) {
                log.error("Phone number not found: {}", user.getEmail_phone());
                return new ResponseEntity<>(new UserResponse("Phone number not found", "ERROR"), HttpStatus.BAD_REQUEST);
            }
        }

        if (!pbkdf2.authenticate(user.getPassword().toCharArray(), userFound.getPassword())) {
            log.error("Password not match: {}", user.getEmail_phone());
            return new ResponseEntity<>(new UserResponse("Password not match", "ERROR"), HttpStatus.BAD_REQUEST);
        }

        log.info("Login successfully: {}", user.getEmail_phone());

        // Generate tokens
        JwtResponse userResponse = new JwtResponse("accessToken", "refreshToken");

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
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

}
